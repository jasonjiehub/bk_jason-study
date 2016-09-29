package com.sogou.study.designStyle.builder;

/**
 * Created by denglinjie on 2016/9/29.
 */
/**
 * 描述输出到文件头的内容的对象
 * @author FX_SKY
 *
 */
public class ExportHeaderModel {

    /**
     * 分公司或者门市编号
     */
    private String depId;
    /**
     * 导出数据的日期
     */
    private String exportDate;

    public String getDepId() {
        return depId;
    }
    public void setDepId(String depId) {
        this.depId = depId;
    }
    public String getExportDate() {
        return exportDate;
    }
    public void setExportDate(String exportDate) {
        this.exportDate = exportDate;
    }

}
