import sys
import os


try:
    os.system("java -jar photonvision.jar") #change photovision.jar to match the filename of your photon jar file.
    print ("Photon Vision has started. Visit wpilibpi.local:5800 (or your Romi's ip address:5800) to tune your pipelines.")
except:
    print("Unexpected error:", sys.exc_info()[0])
    raise
