local now = tonumber(ARGV[1])
local limit = tonumber(ARGV[2])
local visibility = tonumber(ARGV[3])

local items = redis.call(
    "ZRANGEBYSCORE",
    KEYS[1],
    "-inf",
    now,
    "LIMIT",
    0,
    limit
)

if #items == 0 then
    return {}
end

local _until = now + visibility

for _, id in ipairs(items) do
    redis.call("ZREM", KEYS[1], id)
    redis.call("ZADD", KEYS[2], _until, id)
end

return items
