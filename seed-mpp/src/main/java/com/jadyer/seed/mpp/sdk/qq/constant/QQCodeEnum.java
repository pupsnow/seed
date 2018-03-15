package com.jadyer.seed.mpp.sdk.qq.constant;

/**
 * QQ公众平台全局返回码
 * <p>
 *     取自<<QQ公众号API文档.pdf>>-20150907版
 * </p>
 * Created by 玄玉<http://jadyer.cn/> on 2015/11/26 22:39.
 */
public enum QQCodeEnum {
    QQ_MINUS_1 (-1,    "未知错误"),
    QQ_0       (0,     "成功"),
    QQ_30001   (30001, "参数不全"),
    QQ_30002   (30002, "获取OPENID失败"),
    QQ_30003   (30003, "校验token失败"),
    QQ_30004   (30004, "获取用户号码失败"),
    QQ_30005   (30005, "token错误或者该账号没有调用当前接口权限"),
    QQ_30006   (30006, "没有此手机号码对应的QQ号码"),
    QQ_30007   (30007, "客户端不支持此类消息类型"),
    QQ_30008   (30008, "上传图片失败"),
    QQ_30009   (30009, "获取图片Url失败"),
    QQ_30010   (30010, "图文数量不符合"),
    QQ_40001   (40001, "获取access_token时AppSecret错误，或者access_token无效"),
    QQ_40002   (40002, "不合法的凭证类型"),
    QQ_40003   (40003, "不合法的OpenID"),
    QQ_40004   (40004, "不合法的媒体文件类型"),
    QQ_40005   (40005, "不合法的文件类型"),
    QQ_40006   (40006, "不合法的文件大小"),
    QQ_40007   (40007, "不合法的媒体文件id"),
    QQ_40008   (40008, "不合法的消息类型"),
    QQ_40009   (40009, "不合法的图片文件大小"),
    QQ_40010   (40010, "不合法的语音文件大小"),
    QQ_40011   (40011, "不合法的视频文件大小"),
    QQ_40012   (40012, "不合法的缩略图文件大小"),
    QQ_40013   (40013, "不合法的APPID"),
    QQ_40014   (40014, "不合法的access_token"),
    QQ_40015   (40015, "不合法的菜单类型"),
    QQ_40016   (40016, "不合法的按钮数量"),
    QQ_40017   (40017, "不合法的按钮类型"),
    QQ_40018   (40018, "不合法的按钮长度"),
    QQ_40019   (40019, "不合法的按钮KEY长度"),
    QQ_40020   (40020, "不合法的按钮URL长度"),
    QQ_40021   (40021, "不合法的菜单版本号"),
    QQ_40022   (40022, "不合法的子菜单级数"),
    QQ_40023   (40023, "不合法的子菜单按钮个数"),
    QQ_40024   (40024, "不合法的子菜单按钮类型"),
    QQ_40025   (40025, "不合法的子菜单按钮名字长度"),
    QQ_40026   (40026, "不合法的子菜单按钮KEY长度"),
    QQ_40027   (40027, "不合法的子菜单按钮URL长度"),
    QQ_40028   (40028, "不合法的自定义菜单使用用户"),
    QQ_40029   (40029, "不合法的oauth_code"),
    QQ_40030   (40030, "不合法的refresh_token"),
    QQ_40031   (40031, "不合法的openid列表"),
    QQ_40032   (40032, "不合法的openid列表长度"),
    QQ_40033   (40033, "不合法的请求字符，不能包含\\uxxxx格式的字符"),
    QQ_40035   (40035, "不合法的参数"),
    QQ_40038   (40038, "不合法的请求格式"),
    QQ_40039   (40039, "不合法的URL长度"),
    QQ_40050   (40050, "不合法的分组ID"),
    QQ_40051   (40051, "分组名字不合法"),
    QQ_41001   (41001, "缺少access_token参数"),
    QQ_41002   (41002, "缺少APPID参数"),
    QQ_41003   (41003, "缺少refresh_token参数"),
    QQ_41004   (41004, "缺少secret参数"),
    QQ_41005   (41005, "缺少多媒体文件数据"),
    QQ_41006   (41006, "缺少media_id参数"),
    QQ_41007   (41007, "缺少子菜单数据"),
    QQ_41008   (41008, "缺少oauth"),
    QQ_41009   (41009, "缺少openid"),
    QQ_42001   (42001, "access_token超时"),
    QQ_42002   (42002, "refresh_token超时"),
    QQ_42003   (42003, "oauth_code超时"),
    QQ_43001   (43001, "需要GET请求"),
    QQ_43002   (43002, "需要POST请求"),
    QQ_43003   (43003, "需要HTTPS请求"),
    QQ_43004   (43004, "需要接收者关注"),
    QQ_43005   (43005, "需要好友关系"),
    QQ_44001   (44001, "多媒体文件为空"),
    QQ_44002   (44002, "POST 的数据包为空"),
    QQ_44003   (44003, "图文消息内容为空"),
    QQ_44004   (44004, "文本消息内容为空"),
    QQ_45001   (45001, "多媒体文件大小超过限制"),
    QQ_45002   (45002, "消息内容超过限制"),
    QQ_45003   (45003, "标题字段超过限制"),
    QQ_45004   (45004, "描述字段超过限制"),
    QQ_45005   (45005, "链接字段超过限制"),
    QQ_45006   (45006, "图片链接字段超过限制"),
    QQ_45007   (45007, "语音播放时间超过限制"),
    QQ_45008   (45008, "图文消息超过限制"),
    QQ_45009   (45009, "接口调用超过限制"),
    QQ_45010   (45010, "创建菜单个数超过限制"),
    QQ_45015   (45015, "回复时间超过限制"),
    QQ_45016   (45016, "系统分组，不允许修改"),
    QQ_45017   (45017, "分组名字过长"),
    QQ_45018   (45018, "分组数量超过上限"),
    QQ_46001   (46001, "不存在媒体数据"),
    QQ_46002   (46002, "不存在的菜单版本"),
    QQ_46003   (46003, "不存在的菜单数据"),
    QQ_46004   (46004, "不存在的用户"),
    QQ_47001   (47001, "解析JSON/XML内容错误"),
    QQ_48001   (48001, "api功能未授权"),
    QQ_50001   (50001, "用户未授权该api"),
    QQ_80011   (80011, "不合法的window"),
    QQ_60011   (60011, "拉取用户状态时用户不在线"),
    QQ_60020   (60020, "用户版本号过低"),
    QQ_70001   (70001, "模板不存在"),
    QQ_70002   (70002, "模板json错误"),
    QQ_70003   (70003, "模板参数和提交不匹配"),
    QQ_70004   (70004, "模板数量超过六个"),
    QQ_70005   (70005, "模板数量不匹配"),
    QQ_70006   (70006, "消息内容错误"),
    QQ_70007   (70007, "消息类型错误"),
    QQ_70008   (70008, "模板ID和互动号不匹配"),
    QQ_70009   (70009, "用户拒绝接收"),
    QQ_70010   (70010, "错误的按钮数量"),
    QQ_70011   (70011, "错误的按钮命令");

    private final int code;
    private final String msg;

    QQCodeEnum(int code, String msg){
        this.code = code;
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    /**
     * 通过枚举code获取对应的message
     * @return 取不到时返回null
     */
    public static String getMsgByCode(int code){
        for(QQCodeEnum qqCodeEnum : values()){
            if(qqCodeEnum.getCode() == code){
                return qqCodeEnum.getMsg();
            }
        }
        return null;
    }
}