import subprocess
import select
import sys,os
from time import sleep

p = subprocess.Popen(['java','-jar','build/jar/FantasyEmpires.jar'],shell=False, stdin=subprocess.PIPE)

sleep(1)
p.stdin.write('3\n')
sleep(2)
p.stdin.write(' \n')