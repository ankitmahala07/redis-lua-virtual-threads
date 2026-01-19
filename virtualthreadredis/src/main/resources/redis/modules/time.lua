local time = {}

function time.now_ms()
    local t = redis.call("TIME")
    return (t[1] * 1000) + math.floor(t[2] / 1000)
end

return time