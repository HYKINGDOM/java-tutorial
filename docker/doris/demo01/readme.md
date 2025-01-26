




3. 禁用交换内存

交换内存可能会影响 Doris 的性能，建议在宿主机上禁用交换内存。可以通过以下命令临时禁用：

sudo swapoff -a





mysql -h 172.18.0.100 -P 9030 -u root


SET PASSWORD FOR 'root' = PASSWORD('root');
