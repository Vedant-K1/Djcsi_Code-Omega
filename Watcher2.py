import os
import time
from watchdog.observers import Observer
from watchdog.events import FileSystemEventHandler

def log_start_time():
    # Get the start time of the laptop
    start_time = time.strftime("%Y-%m-%d %H:%M:%S", time.localtime())
    # Log the start time in a file
    with open("log.txt", "a") as f:
        f.write("Laptop started at: " + start_time + "\n")

def get_username():
    # Get the username of the logged-in user
    return os.getlogin()

def start_file_monitoring(path_to_monitor, end_time):
    # Create a dictionary to store the accessed files
    accessed_files = {}

    # Define a custom event handler that logs file accesses
    class CustomEventHandler(FileSystemEventHandler):
        def on_modified(self, event):
            if not event.is_directory:
                filename = event.src_path
                accessed_files[filename] = time.strftime("%Y-%m-%d %H:%M:%S", time.localtime())
                with open("log.txt", "a") as f:
                    f.write("File accessed: " + filename + "\n")

    # Create an observer and event handler to monitor the path
    event_handler = CustomEventHandler()
    observer = Observer()
    observer.schedule(event_handler, path_to_monitor, recursive=True)
    observer.start()

    # Monitor file accesses until the specified end time
    while True:
        current_time = time.strftime("%Y-%m-%d %H:%M:%S", time.localtime())
        if current_time > end_time:
            break
        time.sleep(1)

    # Stop the observer when the monitoring period is over
    observer.stop()
    observer.join()

    return accessed_files

def log_end_time_and_accessed_files(accessed_files):
    # Get the end time of the monitoring period
    end_time = time.strftime("%Y-%m-%d %H:%M:%S", time.localtime())

    # Log the end time and accessed files in a file
    with open("log.txt", "a") as f:
        f.write("Monitoring ended at: " + end_time + "\n")
        f.write("Accessed files:\n")
        for filename, access_time in accessed_files.items():
            f.write(filename + " accessed at: " + access_time + "\n")

def run_monitoring():
    # Log the start time
    log_start_time()

    # Get the username and path to monitor
    username = get_username()
    path_to_monitor = "/Users/" + username

    # Define the end time for monitoring file accesses
    end_time = "2023-04-08 21:23:00"

    # Start monitoring file accesses
    accessed_files = start_file_monitoring(path_to_monitor, end_time)

    # Log the end time and accessed files
    log_end_time_and_accessed_files(accessed_files)

if __name__ == "__main__":
    run_monitoring()
