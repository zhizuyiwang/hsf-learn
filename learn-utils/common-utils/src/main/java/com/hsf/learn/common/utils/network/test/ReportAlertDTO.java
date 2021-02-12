package com.hsf.learn.common.utils.network.test;

import com.hsf.learn.common.utils.network.test.ReportAlertMapDTO;

import java.io.Serializable;
import java.util.List;

public class ReportAlertDTO implements Serializable {
    private List<ReportAlertMapDTO> alarmList;
    private List<ReportAlertMapDTO> pictureList;

    public List<ReportAlertMapDTO> getAlarmList() {
        return alarmList;
    }

    public void setAlarmList(List<ReportAlertMapDTO> alarmList) {
        this.alarmList = alarmList;
    }

    public List<ReportAlertMapDTO> getPictureList() {
        return pictureList;
    }

    public void setPictureList(List<ReportAlertMapDTO> pictureList) {
        this.pictureList = pictureList;
    }

    @Override
    public String toString() {
        return "ReportAlertDTO{" +
                "alarmList=" + alarmList +
                ", pictureList=" + pictureList +
                '}';
    }
}
