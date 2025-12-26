package com.zp.framework.file.core.client;

/**
 * Author : zhengpanone
 * Date : 2023/11/17 15:21
 * Version : v1.0.0
 * Description: 文件客户端
 */
public interface FileClient {
    /**
     * 获得客户端编号
     *
     * @return 客户端编号
     */
    Long getId();

    /**
     * 上传文件
     *
     * @param content 文件流
     * @param path    相对路径
     * @return 完整路径，即 HTTP 访问地址
     * @throws Exception 上传文件时，抛出 Exception 异常
     */
    String upload(byte[] content, String path, String type) throws Exception;

    /**
     * 删除文件
     *
     * @param path 相对路径
     * @throws Exception 删除文件时，抛出Exception异常
     */
    void delete(String path) throws Exception;

    /**
     * 获得文件的内容
     *
     * @param path 相对路径
     * @return 文件的内容
     * @throws Exception 获得文件内容时，抛出的异常
     */
    byte[] getContent(String path) throws Exception;
}
