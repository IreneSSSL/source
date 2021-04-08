from watchdog.observers import Observer
from watchdog.events import FileSystemEventHandler
import time
import os

folder_to_track = "D:\\workspace\\python\\movingFiles\\src"
folder_destination_pic = "D:\\workspace\\python\\movingFiles\\des\\pic"
folder_destination_music = "D:\\workspace\\python\\movingFiles\\des\\music"
folder_destination_kaoyan = "D:\\workspace\\python\\movingFiles\\des\\kaoyan"


class MyHandler(FileSystemEventHandler):
    def on_any_event(self, event):
        for filename in os.listdir(folder_to_track) :
            src = folder_to_track +"\\"+ filename
            if filename.endswith("jpg") or filename.endswith("png"):
                new_destination=folder_destination_pic + "\\"+filename
            elif filename.endswith("mp3") :
                new_destination=folder_destination_music + "\\" + filename
            elif filename.__contains__("肖秀荣") :
                new_destination = folder_destination_kaoyan + "\\"+filename
            os.rename(src, new_destination)


event_handler = MyHandler()
observer = Observer()
observer.schedule(event_handler, folder_to_track, recursive=True)
observer.start()
try:
    while True:
        time.sleep(10)
except KeyboardInterrupt:
    observer.stop()

observer.join()
