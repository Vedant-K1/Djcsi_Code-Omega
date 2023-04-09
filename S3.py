from flask import Flask, jsonify, request
import json

app = Flask(__name__)

@app.route('/json-data', methods=['GET', 'POST'])
def get_json_data():
    if request.method == 'GET':
        with open('process_info.json', 'r') as f:
            json_data = json.load(f)
        return jsonify(json_data)
    elif request.method == 'POST':
        with open('process_info.json', 'w') as f:
            json.dump(request.json, f)
        return 'JSON data received'

if __name__ == '__main__':
    app.run(host='15.206.28.159', port=8000)
