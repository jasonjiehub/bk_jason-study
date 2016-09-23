package com.sogou.study.redis.jedisPool;

import org.apache.log4j.Logger;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author denglinjie
 */
public class RedisClient {
	private final static Logger log = Logger.getLogger(RedisClient.class);
	private String serverIP;
	private int port;
	private JedisPool pool = null;
	private int timeout;
	private String auth;
	
	public RedisClient(String serverIP, int port, int timeout, String auth){
		JedisPoolConfig config = new JedisPoolConfig();
		this.serverIP = serverIP;
		this.port = port;
		this.timeout = timeout;
		this.auth = auth;
		if("".equals(auth))
			auth = null;
		pool = new JedisPool(config,serverIP, port,timeout,auth);
	}

	public void rPush(String key ,String value){
		Jedis jedis = null;
		try {
			jedis =  pool.getResource();
			if(jedis == null){
				if(!isAlive()){
					return ;
				}	
			}
			jedis.rpush(key, value);
		}catch(Exception e){
			if(jedis != null)
				pool.returnBrokenResource(jedis);
			log.error("RedisClient zAdd error , ip =" + serverIP + " , port = " + port, e);
		}finally{
			if(jedis != null)
				pool.returnResource(jedis);
		}
	}
	
	public void lPush(String key ,String value){
		Jedis jedis = null;
		try {
			jedis =  pool.getResource();
			if(jedis == null){
				if(!isAlive()){
					return ;
				}	
			}
			jedis.lpush(key, value);
		}catch(Exception e){
			if(jedis != null)
				pool.returnBrokenResource(jedis);
			log.error("RedisClient zAdd error , ip =" + serverIP + " , port = " + port, e);
		}finally{
			if(jedis != null)
			pool.returnResource(jedis);
		}
	
	}
	
	public long lRem(String key,String value){
		Jedis jedis = null;
		try {
			jedis =  pool.getResource();
			if(jedis == null){
				if(!isAlive()){
					return 0l ;
				}
			}
			return jedis.lrem(key, 0,value);
		}catch(Exception e){
			if(jedis != null)
				pool.returnBrokenResource(jedis);
			log.error("RedisClient zAdd error , ip =" + serverIP + " , port = " + port, e);
		}finally{
			if(jedis != null)
				pool.returnResource(jedis);
		}
		return 0l;
	}
	
	public List<String> lRange(String key,long start,long end){
		Jedis jedis = null;
		try {
			jedis =  pool.getResource();
			if(jedis == null){
				if(!isAlive()){
					return null ;
				}	
			}
           return jedis.lrange(key, start, end);
		}catch(Exception e){
			if(jedis != null)
				pool.returnBrokenResource(jedis);
			log.error("RedisClient zAdd error , ip =" + serverIP + " , port = " + port, e);
		}finally{
			if(jedis != null)
				pool.returnResource(jedis);
		}
		return null;
	}

   
    public void zAdd(String key, double score, String value) {

        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            if (jedis == null) {
                return;
            }
            jedis.zadd(key, score, value);
        } catch (Exception e) {
        	if(jedis != null)
				pool.returnBrokenResource(jedis);
            log.error("RedisClient zAdd error , ip =" + serverIP + " , port = " + port, e);
        } finally {
            if (jedis != null)
                pool.returnResource(jedis);
        }
    }

    public Double zincrby(String key, double score, String value) {

        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            if (jedis == null) {
                return 0d;
            }
            return jedis.zincrby(key, score, value);
        } catch (Exception e) {
        	if(jedis != null)
				pool.returnBrokenResource(jedis);
            log.error("RedisClient zAdd error , ip =" + serverIP + " , port = " + port, e);
        } finally {
            if (jedis != null)
                pool.returnResource(jedis);
        }
        return 0d;
    }


    public long zRemByScore(String key, double score) {
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            if (jedis == null) {
                return 0L;
            }
            return jedis.zremrangeByScore(key, score, score);
        } catch (Exception e) {
        	if(jedis != null)
				pool.returnBrokenResource(jedis);
            log.error("RedisClient zRemByScore error , ip =" + serverIP + " , port = " + port, e);
        } finally {
            if (jedis != null)
                pool.returnResource(jedis);
        }
        return 0L;
    }


    public Set<String> zRange(String key, int start, int end) {

        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            if (jedis == null) {
                return null;
            }
            return jedis.zrange(key, start, end);
        } catch (Exception e) {
        	if(jedis != null)
				pool.returnBrokenResource(jedis);
            log.error("RedisClient zRange error , ip =" + serverIP + " , port = " + port, e);
        } finally {
            if (jedis != null)
                pool.returnResource(jedis);
        }
        return null;
    }

    public Long zrank(String key, String member) {

        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            if (jedis == null) {
                return 0l;
            }
            return jedis.zrevrank(key, member);
        } catch (Exception e) {
        	if(jedis != null)
				pool.returnBrokenResource(jedis);
            log.error("RedisClient zRange error , ip =" + serverIP + " , port = " + port, e);
        } finally {
            if (jedis != null)
                pool.returnResource(jedis);
        }
        return 0l;
    }

    public Set<String> zrevrange(String key, int start, int end) {

        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            if (jedis == null) {
                return null;
            }
            return jedis.zrevrange(key, start, end);
        } catch (Exception e) {
        	if(jedis != null)
				pool.returnBrokenResource(jedis);
            log.error("RedisClient zRange error , ip =" + serverIP + " , port = " + port, e);
        } finally {
            if (jedis != null)
                pool.returnResource(jedis);
        }
        return null;
    }

    public Long zreRank(String key, String member) {

        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            if (jedis == null) {
                return -1L;
            }
            Long result= jedis.zrevrank(key, member);
            pool.returnResource(jedis);
            return result;
        } catch (Exception e) {
            if(jedis != null)
                pool.returnBrokenResource(jedis);
            log.error("RedisClient zreRank error , ip =" + serverIP + " , port = " + port, e);
        } finally {
        }
        return -1L;
    }


    public long zLen(String key) {

        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            if (jedis == null) {
                return 0L;
            }
            return jedis.zcard(key);
        } catch (Exception e) {
        	if(jedis != null)
				pool.returnBrokenResource(jedis);
            log.error("RedisClient zLen error , ip =" + serverIP + " , port = " + port, e);
        } finally {
            if (jedis != null)
                pool.returnResource(jedis);
        }
        return 0L;
    }

    public Double zscore(String key, String value) {

        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            if (jedis == null) {
                return (double) 0;
            }
            return jedis.zscore(key, value);
        } catch (Exception e) {
        	if(jedis != null)
				pool.returnBrokenResource(jedis);
            log.error("RedisClient zscore error , ip =" + serverIP + " , port = " + port, e);
        } finally {
            if (jedis != null)
                pool.returnResource(jedis);
        }
        return (double) 0L;
    }

    public Long zcount(String key, Double min, Double max) {

        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            if (jedis == null) {
                return 0L;
            }
            return jedis.zcount(key, min, max);
        } catch (Exception e) {
        	if(jedis != null)
				pool.returnBrokenResource(jedis);
            log.error("RedisClient zcount error , ip =" + serverIP + " , port = " + port, e);
        } finally {
            if (jedis != null)
                pool.returnResource(jedis);
        }
        return 0L;
    }

    public long sAdd(String key, String member) {

        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            if (jedis == null) {
                return 0L;
            }
            return jedis.sadd(key, member);
        } catch (Exception e) {
        	if(jedis != null)
				pool.returnBrokenResource(jedis);
            log.error("RedisClient sAdd error , ip =" + serverIP + " , port = " + port, e);
        } finally {
            if (jedis != null)
                pool.returnResource(jedis);
        }
        return 0L;
    }

    public String get(String key) {
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            if (jedis == null) {
                return null;
            }
            return jedis.get(key);
        } catch (Exception e) {
        	if(jedis != null)
				pool.returnBrokenResource(jedis);
            log.error("RedisClient sAdd error , ip =" + serverIP + " , port = " + port, e);
        } finally {
            if (jedis != null)
                pool.returnResource(jedis);
        }
        return null;

    }

    public long sRem(String key, String member) {

        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            if (jedis == null) {
                return 0L;
            }
            return jedis.srem(key, member);
        } catch (Exception e) {
        	if(jedis != null)
				pool.returnBrokenResource(jedis);
            log.error("RedisClient sRem error , ip =" + serverIP + " , port = " + port, e);
        } finally {
            if (jedis != null)
                pool.returnResource(jedis);
        }
        return 0L;
    }

    public boolean sIsMember(String key, String member) {

        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            if (jedis == null) {
                return false;
            }
            return jedis.sismember(key, member);
        } catch (Exception e) {
        	if(jedis != null)
				pool.returnBrokenResource(jedis);
            log.error("RedisClient sIsMember error , ip =" + serverIP + " , port = " + port, e);
        } finally {
            if (jedis != null)
                pool.returnResource(jedis);
        }
        return false;
    }

    public Long strIncr(String key) {
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            if (jedis == null) {
                return null;
            }
            return jedis.incr(key);
        } catch (Exception e) {
        	if(jedis != null)
				pool.returnBrokenResource(jedis);
            log.error("RedisClient strIncr error , ip =" + serverIP + " , port = " + port, e);
        } finally {
            if (jedis != null)
                pool.returnResource(jedis);
        }
        return null;
    }

    public Long incrBy(String key, long num) {
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            if (jedis == null) {
                return null;
            }
            Long result = jedis.incrBy(key, num);
            pool.returnResource(jedis);
            return result;
        } catch (Exception e) {
            if (jedis != null)
                pool.returnBrokenResource(jedis);
            log.error("RedisClient incrBy error , ip =" + serverIP + " , port = " + port, e);
        }
        return null;
    }

    public Long strdecrBy(String key, long num) {

        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            if (jedis == null) {
                return null;
            }
            return jedis.decrBy(key, num);
        } catch (Exception e) {
        	if(jedis != null)
				pool.returnBrokenResource(jedis);
            log.error("RedisClient strdecrBy error , ip =" + serverIP + " , port = " + port, e);
        } finally {
            if (jedis != null)
                pool.returnResource(jedis);
        }
        return null;
    }

    public Long strdecr(String key) {

        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            if (jedis == null) {
                return null;
            }
            return jedis.decr(key);
        } catch (Exception e) {
        	if(jedis != null)
				pool.returnBrokenResource(jedis);
            log.error("RedisClient strdecr error , ip =" + serverIP + " , port = " + port, e);
        } finally {
            if (jedis != null)
                pool.returnResource(jedis);
        }
        return null;
    }

    	public boolean isAlive(){
		boolean isConnected = false;
		Jedis jedis = null;
		try{
			jedis = pool.getResource();
			if(jedis == null){
				return false;
			}
			isConnected = jedis.isConnected();
		}catch(Exception e){
			if(jedis != null)
				pool.returnBrokenResource(jedis);
			log.error("RedisClient isAlive error , ip =" + serverIP + " , port = " + port, e);
		}finally{
			if(jedis != null)
			pool.returnResource(jedis);
		}
		return isConnected;
	}
    public String set(String key, String value) {

        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            if (jedis == null) {
                return null;
            }
            return jedis.set(key, value);
        } catch (Exception e) {
        	if(jedis != null)
				pool.returnBrokenResource(jedis);
            log.error("RedisClient strdecr error , ip =" + serverIP + " , port = " + port, e);
        } finally {
            if (jedis != null)
                pool.returnResource(jedis);
        }
        return null;
    }

    public Set<String> smembers(String key) {
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            if (jedis == null) {
                return null;
            }
            return jedis.smembers(key);
        } catch (Exception e) {
        	if(jedis != null)
				pool.returnBrokenResource(jedis);
            log.error("RedisClient strdecr error , ip =" + serverIP + " , port = " + port, e);
        } finally {
            if (jedis != null)
                pool.returnResource(jedis);
        }
        return null;
    }

    public long llen(String key) {
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            if (jedis == null) {
                return 0l;
            }
            return jedis.llen(key);
        } catch (Exception e) {
        	if(jedis != null)
				pool.returnBrokenResource(jedis);
            log.error("RedisClient strdecr error , ip =" + serverIP + " , port = " + port, e);
        } finally {
            if (jedis != null)
                pool.returnResource(jedis);
        }
        return 0l;
    }

    public long del(String... keys) {
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            if (jedis == null) {
                return 0L;
            }
            return jedis.del(keys);
        } catch (Exception e) {
        	if(jedis != null)
				pool.returnBrokenResource(jedis);
            log.error("RedisClient del error , ip =" + serverIP + " , port = " + port, e);
        } finally {
            if (jedis != null)
                pool.returnResource(jedis);
        }
        return 0L;
    }

    public Set<String> keys(String pattern) {
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            if (jedis == null) {
                return null;
            }
            return jedis.keys(pattern);
        } catch (Exception e) {
        	if(jedis != null)
				pool.returnBrokenResource(jedis);
            log.error("RedisClient keys error , ip =" + serverIP + " , port = " + port, e);
        } finally {
            if (jedis != null)
                pool.returnResource(jedis);
        }
        return null;
    }

    public boolean exists(String pattern) {
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            if (jedis == null) {
                return false;
            }
            return jedis.exists(pattern);
        } catch (Exception e) {
        	if(jedis != null)
				pool.returnBrokenResource(jedis);
            log.error("RedisClient keys error , ip =" + serverIP + " , port = " + port, e);
        } finally {
            if (jedis != null)
                pool.returnResource(jedis);
        }
        return false;
    }

    public Long expire(String key, int ttl) {
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            if (jedis == null) {
                return null;
            }
            return jedis.expire(key, ttl);
        } catch (Exception e) {
        	if(jedis != null)
				pool.returnBrokenResource(jedis);
            log.error("RedisClient expire error , ip =" + serverIP + " , port = " + port, e);
        } finally {
            if (jedis != null)
                pool.returnResource(jedis);
        }
        return null;
    }

    public String srandomMember(String key) {
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            if (jedis == null) {
                return null;
            }
            return jedis.srandmember(key);
        } catch (Exception e) {
        	if(jedis != null)
				pool.returnBrokenResource(jedis);
            log.error("RedisClient srandomemember error , ip =" + serverIP + " , port = " + port, e);
        } finally {
            if (jedis != null)
                pool.returnResource(jedis);
        }
        return null;
    }


    public List<String> srandomMember(String key, int size) {
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            if (jedis == null) {
                return null;
            }
            List<String> list = jedis.srandmember(key, size);
            if (jedis != null)
                pool.returnResource(jedis);
            return list;
        } catch (Exception e) {
            if (jedis != null)
                pool.returnBrokenResource(jedis);
            log.error("RedisClient srandomemember error , ip =" + serverIP + " , port = " + port, e);
        }
        return null;
    }


    public String hmset(String key, Map<String, String> value) {

        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            if (jedis == null) {
                return null;
            }
            return jedis.hmset(key, value);
        } catch (Exception e) {
        	if(jedis != null)
				pool.returnBrokenResource(jedis);
            log.error("RedisClient strdecr error , ip =" + serverIP + " , port = " + port, e);
        } finally {
            if (jedis != null)
                pool.returnResource(jedis);
        }
        return null;
    }

    public List<String> hmget(String key, String... fields) {

        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            if (jedis == null) {
                return null;
            }
            return jedis.hmget(key, fields);
        } catch (Exception e) {
        	if(jedis != null)
				pool.returnBrokenResource(jedis);
            log.error("RedisClient strdecr error , ip =" + serverIP + " , port = " + port, e);
        } finally {
            if (jedis != null)
                pool.returnResource(jedis);
        }
        return null;
    }

    public Set<String> hkeys(String key) {

        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            if (jedis == null) {
                return null;
            }
            return jedis.hkeys(key);
        } catch (Exception e) {
        	if(jedis != null)
				pool.returnBrokenResource(jedis);
            log.error("RedisClient strdecr error , ip =" + serverIP + " , port = " + port, e);
        } finally {
            if (jedis != null)
                pool.returnResource(jedis);
        }
        return null;
    }

    public Map<String, String> hgetAll(String key) {

        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            if (jedis == null) {
                return null;
            }
            return jedis.hgetAll(key);
        } catch (Exception e) {
        	if(jedis != null)
				pool.returnBrokenResource(jedis);
            log.error("RedisClient strdecr error , ip =" + serverIP + " , port = " + port, e);
        } finally {
            if (jedis != null)
                pool.returnResource(jedis);
        }
        return null;
    }

    public long zrem(String key, String[] members) {
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            if (jedis == null) {
                return 0L;
            }
            return jedis.zrem(key, members);
        } catch (Exception e) {
        	if(jedis != null)
				pool.returnBrokenResource(jedis);
            log.error("RedisClient zrem error , ip =" + serverIP + " , port = " + port, e);
        } finally {
            if (jedis != null)
                pool.returnResource(jedis);
        }
        return 0L;
    }

    public Set<String> zrangeByScore(String key, double min, double max) {
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            if (jedis == null) {
                return null;
            }
            return jedis.zrangeByScore(key, min, max);
        } catch (Exception e) {
        	if(jedis != null)
				pool.returnBrokenResource(jedis);
            log.error("RedisClient zrangeByScore error , ip =" + serverIP + " , port = " + port, e);
        } finally {
            if (jedis != null)
                pool.returnResource(jedis);
        }
        return null;
    }

    public long srem(String key, String[] members) {
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            if (jedis == null) {
                return 0L;
            }
            return jedis.srem(key, members);
        } catch (Exception e) {
        	if(jedis != null)
				pool.returnBrokenResource(jedis);
            log.error("RedisClient sRem members error , ip =" + serverIP + " , port = " + port, e);
        } finally {
            if (jedis != null)
                pool.returnResource(jedis);
        }
        return 0L;
    }

    public long hset(String key, String field, String value) {
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            if (jedis == null) {
                return 0;
            }
            return jedis.hset(key, field, value);
        } catch (Exception e) {
        	if(jedis != null)
				pool.returnBrokenResource(jedis);
            log.error("RedisClient hset error , ip =" + serverIP + " , port = "
                    + port, e);
        } finally {
            if (jedis != null)
                pool.returnResource(jedis);
        }
        return 0;
    }

    public String hget(String key, String field) {
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            if (jedis == null) {
                return null;
            }
            return jedis.hget(key, field);
        } catch (Exception e) {
        	if(jedis != null)
				pool.returnBrokenResource(jedis);
            log.error("RedisClient hget error , ip =" + serverIP + " , port = "
                    + port, e);
        } finally {
            if (jedis != null)
                pool.returnResource(jedis);
        }
        return null;
    }

    public List<String> hvals(String key) {
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            if (jedis == null) {
                return null;
            }
            return jedis.hvals(key);
        } catch (Exception e) {
        	if(jedis != null)
				pool.returnBrokenResource(jedis);
            log.error("RedisClient hvals error , ip =" + serverIP + " , port = "
                    + port, e);
        } finally {
            if (jedis != null)
                pool.returnResource(jedis);
        }
        return null;
    }

    public long hlen(String key) {
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            if (jedis == null) {
                return 0L;
            }
            return jedis.hlen(key);
        } catch (Exception e) {
        	if(jedis != null)
				pool.returnBrokenResource(jedis);
            log.error("RedisClient hlen error , ip =" + serverIP + " , port = "
                    + port, e);
        } finally {
            if (jedis != null)
                pool.returnResource(jedis);
        }
        return 0L;
    }

    public long hdel(String key, String[] fields) {
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            if (jedis == null) {
                return 0L;
            }
            return jedis.hdel(key, fields);
        } catch (Exception e) {
        	if(jedis != null)
				pool.returnBrokenResource(jedis);
            log.error("RedisClient hdel error , ip =" + serverIP + " , port = "
                    + port, e);
        } finally {
            if (jedis != null)
                pool.returnResource(jedis);
        }
        return 0L;
    }

    public Long ttl(String key) {
        Jedis jedis = null;
        try {
            jedis = pool.getResource();
            if (jedis == null) {
                return 0L;
            }
            return jedis.ttl(key);
        } catch (Exception e) {
        	if(jedis != null)
				pool.returnBrokenResource(jedis);
            log.error("RedisClient ttl error , ip =" + serverIP + " , port = " + port, e);
        } finally {
            if (jedis != null)
                pool.returnResource(jedis);
        }
        return 0L;
    }
}
