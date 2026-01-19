local validation = {}

function validation.non_empty(value)
    return value ~= nil and value ~= false and value ~= ""
end

function validation.positive_number(n)
    return n and tonumber(n) and tonumber(n) > 0
end

return validation