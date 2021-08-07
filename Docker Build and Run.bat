@echo This batch file requires you to have docker installed. It is designed for convenience.
docker build -t batchrun/docker-employee-pay-slip:latest .
docker run -d -p 8080:8080 batchrun/docker-employee-pay-slip:latest