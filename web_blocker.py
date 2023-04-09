import time
from datetime import datetime as dt

# hosts_path = "/etc/hosts"  # Path of the hosts file on Linux/Mac OS
hosts_path = r"C:\Windows\System32\drivers\etc\hosts"  # Path of the hosts file on Windows
redirect = "127.0.0.1"
website_list = ["www.rummycircle.com", "rummycircle.com", "www.omegle.com", "www.omegle.com","www.facebook.com", "facebook.com", "www.youtube.com", "youtube.com","www.tinder.com","tinder.com","tinder"]  # List of websites to block
while True:
    # Check if the current time is within the working hours
    if (1>0):
        print("Websites blocked successfully")
        with open(hosts_path, "r+") as file:
            content = file.read()
            for website in website_list:
                if website in content:
                    pass
                else:
                    file.write(redirect + " " + website + "\n")
    else:
        with open(hosts_path, "r+") as file:
            content = file.readlines()
            file.seek(0)
            for line in content:
                if not any(website in line for website in website_list):
                    file.write(line)
            file.truncate()
    time.sleep(60)  # Wait for 1 minute before checking again