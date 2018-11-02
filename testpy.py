#!/usr/bin/python
#coding=utf-8
# decode for python2.7
# unicode to str
s='\u8fd9\u662f\u4e00\u4e2a\u0075\u006e\u0069\u0063\u006f\u0064\u0065\u89e3\u7801\u6d4b\u8bd5'
print s.decode('unicode_escape')
# unicode to utf-8
print 'utf-8 encode: '+s.encode('utf-8')
# utf-8 to unicode
print 'utf-8 decode: '+s.encode('utf-8').decode('utf-8')
