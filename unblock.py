import platform

# get the path to the hosts file based on the current operating system
if platform.system() == "Windows":
    hosts_path = r"C:\Windows\System32\drivers\etc\hosts"
elif platform.system() == "Linux":
    hosts_path = "/etc/hosts"
else:
    print("Unsupported operating system")
    exit()

# the websites to unblock
website_list = ["www.rummycircle.com", "rummycircle.com", "www.omegle.com", "www.omegle.com","www.facebook.com", "facebook.com", "www.youtube.com", "youtube.com","www.tinder.com","tinder.com","tinder"]

# open the hosts file for reading and writing
with open(hosts_path, "r+") as file:
    # read the contents of the hosts file
    content = file.readlines()
    # reset the file pointer to the beginning of the file
    file.seek(0)
    # iterate over the lines of the file and write back only the lines that do not contain the websites to unblock
    for line in content:
        if not any(website in line for website in website_list):
            file.write(line)
    # truncate the remaining content of the file
    file.truncate()

print("Websites unblocked successfully.")
