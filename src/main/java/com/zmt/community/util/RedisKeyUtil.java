package com.zmt.community.util;

/**
 *
 */
public class RedisKeyUtil {
    //key的拼接符号：
    private static final String SPLIT = ":";
    //entity表示帖子、评论，like是前缀
    private static final String PREFIX_ENTITY_LIKE = "like:entity";
    //用户的获赞数
    private static final String PREFIX_USER_LIKE = "like:user";
    //若A关注了B，则A是B的Follower（粉丝），B是A的Followee（目标）
    //用户关注目标
    private static final String PREFIX_FOLLOWEE = "followee";
    //关注用户的人
    private static final String PREFIX_FOLLOWER = "follower";
    //验证码
    private static final String PREFIX_KAPTCHA = "kaptcha";
    //登录凭证
    private static final String PREFIX_TICKET = "ticket";
    //用户信息
    private static final String PREFIX_USER = "user";
    private static final String PREFIX_UV = "uv";
    private static final String PREFIX_DAU = "dau";
    private static final String PREFIX_POST = "post";



    /**
     * 获取表示实体点赞信息的Key，点赞信息用set存储
     *    key是实体，value是该点赞该实体的用户userId集合，显示评论者
     * @param entityType 实体类型，帖子 or 评论
     * @param entityId 实体id
     * @return 表示某个实体的key：like:entity:entityType:entityId
     */
    public static String getEntityLikeKey(int entityType, int entityId) {
        return PREFIX_ENTITY_LIKE + SPLIT + entityType + SPLIT + entityId;
    }

    /**
     * 获取保存用户获赞数的key
     * @param userId 用户id
     * @return 获赞数
     */
    public static String getUserLikeKey(int userId) {
        return PREFIX_USER_LIKE + SPLIT + userId;
    }

    /**
     * 获取保存用户关注的实体信息的key，实体可以是帖子、用户、评论等
     * @param userId 用户
     * @param entityType 实体
     * @return key
     */
    public static String getFolloweeKey(int userId, int entityType) {
        // followee:userId:entityType -> zset(entityId,now)
        //通过entityType可以对同一个用户关注的不同实体分类
        //使用zset存储，以当前时间为分数可以按时间列出关注对象
        return PREFIX_FOLLOWEE + SPLIT + userId + SPLIT + entityType;
    }

    /**
     * 获取保存实体拥有粉丝的eky zset保存，以时间为分数
     * @param entityType 类型
     * @param entityId id
     * @return key
     */
    public static String getFollowerKey(int entityType, int entityId) {
        // follower:entityType:entityId -> zset(userId,now)
        return PREFIX_FOLLOWER + SPLIT + entityType + SPLIT + entityId;
    }

    /**
     * 用户获取时获取验证码的key
     * @param owner 登陆时发送给用户的一个标识用户的随机字符串，存放在cookie中
     * @return
     */
    public static String getKaptchaKey(String owner) {
        return PREFIX_KAPTCHA + SPLIT + owner;
    }

    /**
     *  获取登录的凭证的key
     * @param ticket 凭证的key
     * @return
     */
    public static String getTicketKey(String ticket) {
        return PREFIX_TICKET + SPLIT + ticket;
    }

    /**
     * 获取缓存用户信息的key
     * @param userId
     * @return key
     */
    public static String getUserKey(int userId) {
        return PREFIX_USER + SPLIT + userId;
    }

    // 单日UV
    public static String getUVKey(String date) {
        return PREFIX_UV + SPLIT + date;
    }

    // 区间UV
    public static String getUVKey(String startDate, String endDate) {
        return PREFIX_UV + SPLIT + startDate + SPLIT + endDate;
    }

    // 单日活跃用户
    public static String getDAUKey(String date) {
        return PREFIX_DAU + SPLIT + date;
    }

    // 区间活跃用户
    public static String getDAUKey(String startDate, String endDate) {
        return PREFIX_DAU + SPLIT + startDate + SPLIT + endDate;
    }

    // 帖子分数
    public static String getPostScoreKey() {
        return PREFIX_POST + SPLIT + "score";
    }

}
