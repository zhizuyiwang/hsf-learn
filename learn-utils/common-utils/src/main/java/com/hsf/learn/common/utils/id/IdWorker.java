/**
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.hsf.learn.common.utils.id;

import org.apache.commons.lang3.StringUtils;

/**
 * 0 -00000000 00- 0000000000 0000000000 0000000000 0000000000 0 -000- 00000000 0
 * (1)    (2)                      (3)                            （4）     （5）
 * <p>
 * (1) 未使用（实际上也可作为long的符号位）  ======占用1位
 * (2) 用户ID的hashcode 1024取模的值  ======占用10位
 * (3) 毫秒级时间  ======占用41位
 * (4)机器ID ======占用3位
 * (5)毫秒内的当前毫秒内的计数 ======占用9位
 * 以上加起来刚好64位，为一个Long型。
 */
public class IdWorker {

    /**
     * 机器标识
     */
    private long machineId;

    /**
     * 自增序列号
     */
    private long sequence;

    /**
     * 用户IdHash
     */
    private long userIdHash;

    /**
     * 上一次时间戳
     */
    private long lastTimestamp = -1L;

    /**
     * @param machineId 机器码 范围（0-7）
     */
    public IdWorker(long machineId, long userIdHash) {
        if (machineId < 0 || machineId > IdConstant.MAX_MACHINE_NUM) {
            throw new IllegalArgumentException("machineId can't be greater than " + IdConstant.MAX_MACHINE_NUM + " or less than 0");
        }
        if (userIdHash < 0 | userIdHash > IdConstant.DEFAULT_USER_HASH_SIZE) {
            throw new IllegalArgumentException("userIdHash can't be greater than " + IdConstant.DEFAULT_USER_HASH_SIZE + " or less than 0");
        }
        // 用户ID的hashCode取模
        this.machineId = machineId;
        this.userIdHash = userIdHash;
    }

    /**
     * 产生一个ID
     *
     * @return
     */
    public synchronized long nextId(String userId) {
        if (StringUtils.isBlank(userId)) {
            throw new IllegalStateException("userId can not be null!");
        }
        int userIdHashcode = Math.abs((userId.hashCode() >> IdConstant.DEFAULT_USER_ID_SHIFT) % IdConstant.DEFAULT_USER_HASH_SIZE);
        if (userIdHash != userIdHashcode) {
            throw new IllegalStateException("userId hashCode incorrect, please check init IdWorkFactory!");
        }

        long timestamp = timeGen();
        if (timestamp < lastTimestamp) {
            throw new IllegalStateException("Clock moved backwards!");
        }
        // 如果上一个timestamp与新产生的相等，则sequence加一(0-511循环); 对新的timestamp，sequence从0开始
        if (lastTimestamp == timestamp) {
            sequence = (sequence + 1) & IdConstant.MAX_SEQUENCE_NUM;
            if (sequence == 0) {
                // 重新生成timestamp
                timestamp = tilNextMillis(lastTimestamp);
            }
        } else {
            sequence = 0;
        }
        lastTimestamp = timestamp;

        long id = userIdHash << IdConstant.USER_ID_LEFT_SHIFT
                | ((timestamp - IdConstant.IDEPOCH) << IdConstant.TIMESTAMP_LEFT_SHIFT)
                | machineId << IdConstant.SEQUENCE_BIT
                | sequence;
        return Long.MAX_VALUE - id;
    }

    /**
     * 等待下一个毫秒的到来, 保证返回的毫秒数在参数lastTimestamp之后
     */
    private long tilNextMillis(long lastTimestamp) {
        long timestamp = timeGen();
        while (timestamp <= lastTimestamp) {
            timestamp = timeGen();
        }
        return timestamp;
    }

    /**
     * 获得系统当前毫秒数
     */
    private long timeGen() {
        return System.currentTimeMillis();
    }

    /**
     * 获取hashCode
     * @param val
     * @return
     */
    public static long getHashCode(String val){
        return Math.abs((val.hashCode() >> IdConstant.DEFAULT_USER_ID_SHIFT) % IdConstant.DEFAULT_USER_HASH_SIZE);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("IdWorker{");
        sb.append(", idepoch=").append(IdConstant.IDEPOCH);
        sb.append(", lastTimestamp=").append(lastTimestamp);
        sb.append(", sequence=").append(sequence);
        sb.append('}');
        return sb.toString();
    }

    public static void main(String[] args) {
        IdWorker idWorker = new IdWorker(1, IdWorker.getHashCode(IdConstant.ID_MODE_DEPT));
        for(int i = 0; i < 10000; i ++){
            System.out.println(idWorker.nextId(IdConstant.ID_MODE_DEPT));
        }
    }

}
