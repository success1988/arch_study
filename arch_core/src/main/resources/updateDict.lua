if redis.call("del",KEYS[1]) then
	redis.call('hmset', KEYS[1], unpack(ARGV))
	return true;
else
	return false
end