import time
def logWrite(log_msg, logFile):
	logFile.write(log_msg)
def main():
	with open ("debug.log","w") as logFile:
		sysn = 0;
		while sysn < 10000:
			log_msg = "this is log msg %d \n" % sysn
			logWrite(log_msg, logFile)
			# time.sleep(1)
			sysn+=1
	    	pass
if __name__ == '__main__':
	main()
