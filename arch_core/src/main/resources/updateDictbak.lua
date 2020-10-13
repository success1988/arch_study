
for i, v in ipairs(ARGV) do
    ARGV[i] = string.gsub(v,'\"','')
end

if redis.call("del",KEYS[1]) then
	return redis.call('hmset', KEYS[1], unpack(ARGV))
else
	return 0
end