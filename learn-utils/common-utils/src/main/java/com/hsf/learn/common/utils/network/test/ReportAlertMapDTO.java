package com.hsf.learn.common.utils.network.test;

import java.io.Serializable;

/**
 *
 */
public class ReportAlertMapDTO implements Serializable {

    /**
     * 设备ID
     */
    private Long deviceId;

    private String imei;

    private String samplingTime;

    private String cachePath;

    private String aiCachePath;

    //摄像头编号
    private Integer cameraNo;

    //原图路径
    private String metaCachePath;

    //预警最新一组图片的组号
    private String groupNo;

    //省
    private String province;
    //市
    private String city;
    //区
    private String district;

    private String content;

    private String deviceName;
    //线路名称
    private String routeName;

    private String proName;

    private String distanceAlarm;
    /**告警编号*/
    private String alarmNum;

    /**告警信息*/
    private String alarmName;

    private Integer cameraNum;

    private Float lineTemperature;

    private Float enTemperature;

    private Float enHumidity;

    private String title;

    public Long getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Long deviceId) {
        this.deviceId = deviceId;
    }

    public String getImei() {
        return imei;
    }

    public void setImei(String imei) {
        this.imei = imei;
    }

    public String getSamplingTime() {
        return samplingTime;
    }

    public void setSamplingTime(String samplingTime) {
        this.samplingTime = samplingTime;
    }

    public String getCachePath() {
        return cachePath;
    }

    public void setCachePath(String cachePath) {
        this.cachePath = cachePath;
    }

    public String getAiCachePath() {
        return aiCachePath;
    }

    public void setAiCachePath(String aiCachePath) {
        this.aiCachePath = aiCachePath;
    }

    public Integer getCameraNo() {
        return cameraNo;
    }

    public void setCameraNo(Integer cameraNo) {
        this.cameraNo = cameraNo;
    }

    public String getMetaCachePath() {
        return metaCachePath;
    }

    public void setMetaCachePath(String metaCachePath) {
        this.metaCachePath = metaCachePath;
    }

    public String getGroupNo() {
        return groupNo;
    }

    public void setGroupNo(String groupNo) {
        this.groupNo = groupNo;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getDistrict() {
        return district;
    }

    public void setDistrict(String district) {
        this.district = district;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getRouteName() {
        return routeName;
    }

    public void setRouteName(String routeName) {
        this.routeName = routeName;
    }

    public String getProName() {
        return proName;
    }

    public void setProName(String proName) {
        this.proName = proName;
    }

    public String getDistanceAlarm() {
        return distanceAlarm;
    }

    public void setDistanceAlarm(String distanceAlarm) {
        this.distanceAlarm = distanceAlarm;
    }

    public String getAlarmNum() {
        return alarmNum;
    }

    public void setAlarmNum(String alarmNum) {
        this.alarmNum = alarmNum;
    }

    public String getAlarmName() {
        return alarmName;
    }

    public void setAlarmName(String alarmName) {
        this.alarmName = alarmName;
    }

    public Integer getCameraNum() {
        return cameraNum;
    }

    public void setCameraNum(Integer cameraNum) {
        this.cameraNum = cameraNum;
    }

    public Float getLineTemperature() {
        return lineTemperature;
    }

    public void setLineTemperature(Float lineTemperature) {
        this.lineTemperature = lineTemperature;
    }

    public Float getEnTemperature() {
        return enTemperature;
    }

    public void setEnTemperature(Float enTemperature) {
        this.enTemperature = enTemperature;
    }

    public Float getEnHumidity() {
        return enHumidity;
    }

    public void setEnHumidity(Float enHumidity) {
        this.enHumidity = enHumidity;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public String toString() {
        return "ReportAlertMapDTO{" +
                "deviceId=" + deviceId +
                ", imei='" + imei + '\'' +
                ", samplingTime='" + samplingTime + '\'' +
                ", cachePath='" + cachePath + '\'' +
                ", aiCachePath='" + aiCachePath + '\'' +
                ", cameraNo=" + cameraNo +
                ", metaCachePath='" + metaCachePath + '\'' +
                ", groupNo='" + groupNo + '\'' +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", district='" + district + '\'' +
                ", content='" + content + '\'' +
                ", deviceName='" + deviceName + '\'' +
                ", routeName='" + routeName + '\'' +
                ", proName='" + proName + '\'' +
                ", distanceAlarm='" + distanceAlarm + '\'' +
                ", alarmNum='" + alarmNum + '\'' +
                ", alarmName='" + alarmName + '\'' +
                ", cameraNum=" + cameraNum +
                ", lineTemperature=" + lineTemperature +
                ", enTemperature=" + enTemperature +
                ", enHumidity=" + enHumidity +
                ", title='" + title + '\'' +
                '}';
    }
}
