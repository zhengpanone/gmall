package com.zp.framework.file.core.client.db;

import com.zp.framework.file.core.client.AbstractFileClient;
import lombok.Data;

/**
 * Author : zhengpanone
 * Date : 2023/11/17 16:31
 * Version : v1.0.0
 
 */

public class DBFileClient extends AbstractFileClient<DBFileClientConfig> {

    public DBFileClient(Long id, DBFileClientConfig config) {
        super(id, config);
    }

    /**
     * 自定义初始化
     */
    @Override
    protected void doInit() {
    }

    /**
     * 获得客户端编号
     *
     * @return 客户端编号
     */
    @Override
    public Long getId() {
        return null;
    }

    /**
     * 上传文件
     *
     * @param content 文件流
     * @param path    相对路径
     * @param type
     * @return 完整路径，即 HTTP 访问地址
     * @throws Exception 上传文件时，抛出 Exception 异常
     */
    @Override
    public String upload(byte[] content, String path, String type) throws Exception {
        return null;
    }

    /**
     * 删除文件
     *
     * @param path 相对路径
     * @throws Exception 删除文件时，抛出Exception异常
     */
    @Override
    public void delete(String path) throws Exception {

    }

    /**
     * 获得文件的内容
     *
     * @param path 相对路径
     * @return 文件的内容
     * @throws Exception 获得文件内容时，抛出的异常
     */
    @Override
    public byte[] getContent(String path) throws Exception {
        return new byte[0];
    }
}
