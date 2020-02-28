package awd.cloud.internet.app.smsy.utils;

/**
 * 提供默认支持的查询条件类型,用于动态指定查询条件
 *
 */
public interface TermType {
    /**
     * ==
     *
     * @since 1.0
     */
    String eq      = "eq";
    /**
     * !=
     *
     * @since 1.0
     */
    String not     = "not";
    /**
     * like
     *
     * @since 1.0
     */
    String like    = "like";
    /**
     * not like
     *
     * @since 1.0
     */
    String nlike   = "nlike";
    /**
     * >
     *
     * @since 1.0
     */
    String gt      = "gt";
    /**
     * <
     *
     * @since 1.0
     */
    String lt      = "lt";
    /**
     * >=
     *
     * @since 1.0
     */
    String gte     = "gte";
    /**
     * <=
     *
     * @since 1.0
     */
    String lte     = "lte";
    /**
     * in
     *
     * @since 1.0
     */
    String in      = "in";
    /**
     * notin
     *
     * @since 1.0
     */
    String nin     = "nin";
    /**
     * =''
     *
     * @since 1.0
     */
    String empty   = "empty";
    /**
     * !=''
     *
     * @since 1.0
     */
    String nempty  = "nempty";
    /**
     * is null
     *
     * @since 1.0
     */
    String isnull  = "isnull";
    /**
     * not null
     *
     * @since 1.0
     */
    String notnull = "notnull";
    /**
     * between
     *
     * @since 1.0
     */
    String btw     = "btw";
    /**
     * not between
     *
     * @since 1.0
     */
    String nbtw    = "nbtw";

    /**
     * 此类型将直接执行sql.在类型是从客户端参数中获取的场景中,应该屏蔽此类型
     *
     * @see SqlTerm
     * @since 1.0
     * @deprecated 此属性已弃用，如果想直接拼接sql，请使用 {@link SqlTerm}
     */
    @Deprecated
    String func = "func";
}
