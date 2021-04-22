# RomiVision
This will be our example implementation of Vision tracking and object following using the Romi.
Currently, it only has a turn-to-angle command as we are attempting to get that wired before adding the vision layer.

Our vision layer will probably use Photon Vision as it is easy to tune, and with a few tweaks works well on the Romi.
# Using Photon...
Download the latest Photon.jar
Use Winscp to move it to the Romi (alternatively, if the ROmi is online, run wget path to photonbuild.jar)

then, make the image writeable, and upload the Python file. Finally, visit wpilibpi:5800