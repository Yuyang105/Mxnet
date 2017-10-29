# Write your MySQL query statement below
update salary set sex = char(ascii(sex) ^ ascii('f') ^ ascii('m'));

# update salary set sex = if(sex = 'm', 'f', 'm');
