package com.sogou.study.designStyle.builder;

import java.util.List;
import java.util.Map;

/**
 * Created by denglinjie on 2016/9/29.
 */
public interface Builder {

    /**
     * 构建输出文件的Header部分
     * @param ehm
     */
    void buildHeader(ExportHeaderModel ehm);

    /**
     * 构建输出文件的Body部分
     * @param mapData
     */
    void buildBody(Map<String,List<ExportDataModel>> mapData);

    /**
     * 构建输出文件的Footer部分
     * @param efm
     */
    void buildFooter(ExportFooterModel efm);

}
