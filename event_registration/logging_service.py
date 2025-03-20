import requests

SPRING_BOOT_LOGGING_URL = "http://localhost:8080/api/logs"

def log_info(log_type, message):
    payload = {"type": log_type, "message": message}
    try:
        response = requests.post(f"{SPRING_BOOT_LOGGING_URL}/info", json=payload, timeout=5)
        response.raise_for_status()
    except requests.RequestException as e:
        print(f"Logging Error: {e}")

def log_error(error_type, message):
    payload = {"type": error_type, "message": message}
    try:
        response = requests.post(f"{SPRING_BOOT_LOGGING_URL}/error", json=payload, timeout=5)
        response.raise_for_status()
    except requests.RequestException as e:
        print(f"Logging Error: {e}")
