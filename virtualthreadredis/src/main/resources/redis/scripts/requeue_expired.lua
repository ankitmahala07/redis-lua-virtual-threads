local now = tonumber(ARGV[1])
local limit = tonumber(ARGV[2])

local expired = redis.call(
    "ZRANGEBYSCORE",
    KEYS[1],
    "-inf",
    now,
    "LIMIT",
    0,
    limit
)

for _, id in ipairs(expired) do
    redis.call("ZREM", KEYS[1], id)
    redis.call("ZADD", KEYS[2], now, id)
end

return expired
