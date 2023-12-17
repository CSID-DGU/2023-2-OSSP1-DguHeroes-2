FROM python:3.8.5

RUN mkdir -p /opt/sonarcube-repeat
WORKDIR /opt/sonarcube-repeat
COPY . .
RUN pip install -r ./requirements.txt

EXPOSE 8000
CMD python main.py