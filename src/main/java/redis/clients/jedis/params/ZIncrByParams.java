package redis.clients.jedis.params;

import redis.clients.jedis.CommandArguments;

/**
 * Parameters for ZINCRBY commands. In fact, Redis doesn't have parameters for ZINCRBY. Instead
 * Redis has INCR parameter for ZADD.
 * <p>
 * When users call ZADD with INCR option, its restriction (only one member) and return type is same
 * to ZINCRBY. Document page for ZADD also describes INCR option to act like ZINCRBY. So we decided
 * to wrap "ZADD with INCR option" to ZINCRBY.
 * <p>
 * Works with Redis 3.0.2 and onwards.
 */
public class ZIncrByParams extends Params implements IParams {

  private static final String XX = "xx";
  private static final String NX = "nx";
  private static final String INCR = "incr";

  public ZIncrByParams() {
  }

  public static ZIncrByParams zIncrByParams() {
    return new ZIncrByParams();
  }

  /**
   * Only set the key if it does not already exist.
   * @return ZIncrByParams
   */
  public ZIncrByParams nx() {
    addParam(NX);
    return this;
  }

  /**
   * Only set the key if it already exist.
   * @return ZIncrByParams
   */
  public ZIncrByParams xx() {
    addParam(XX);
    return this;
  }

  @Override
  public void addParams(CommandArguments args) {
    if (contains(NX)) {
      args.add(NX);
    }
    if (contains(XX)) {
      args.add(XX);
    }

    args.add(INCR);
  }

}
