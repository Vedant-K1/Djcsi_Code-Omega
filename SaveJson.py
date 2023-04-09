import win32gui
import win32process
import psutil
import json
import datetime

def get_active_window_pid():
    """Returns a dictionary of process information for the currently active window."""
    process = psutil.Process(win32process.GetWindowThreadProcessId(win32gui.GetForegroundWindow())[1])
    process_info = process.as_dict(attrs=['name', 'status', 'create_time'])
    start_time = datetime.datetime.fromtimestamp(process_info['create_time'])
    process_info['create_time'] = start_time.strftime('%Y-%m-%d %H:%M:%S')

    return process_info

previous_process_name = ""
process_list = []

while True:
    try:
        process_name = get_active_window_pid()
        if process_name != previous_process_name:
            previous_process_name = process_name
            print(process_name)
            process_list.append(process_name)
            with open("process_info.json", "w") as f:
                json.dump(process_list, f)
    except:
        pass

