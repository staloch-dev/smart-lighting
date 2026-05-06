#include <WiFi.h>
#include <HTTPClient.h>

const int LDR_PIN = 34;
const int LED_PIN = 5;
const int BTN_PIN = 15;

const char* ssid     = "Wokwi-GUEST";
const char* password = "";

// Substitui pela URL do seu Cloudflare quando estiver rodando
const char* sensorUrl = "https://SUA-URL.trycloudflare.com/api/sensor";
const char* switchUrl = "https://SUA-URL.trycloudflare.com/api/interruptor";

bool lastSwitchState = false;

void setup() {
  Serial.begin(115200);
  pinMode(LED_PIN, OUTPUT);
  pinMode(BTN_PIN, INPUT_PULLUP);
  digitalWrite(LED_PIN, LOW);

  WiFi.begin(ssid, password);
  Serial.print("Conectando WiFi");
  while (WiFi.status() != WL_CONNECTED) {
    delay(500);
    Serial.print(".");
  }
  Serial.println("\nWiFi conectado!");
}

void postJson(const char* url, String body) {
  HTTPClient http;
  http.begin(url);
  http.addHeader("Content-Type", "application/json");
  http.POST(body);
  http.end();
}

String postJsonResponse(const char* url, String body) {
  HTTPClient http;
  http.begin(url);
  http.addHeader("Content-Type", "application/json");
  int code = http.POST(body);
  String response = "";
  if (code == 200) {
    response = http.getString();
  }
  http.end();
  return response;
}

void loop() {
  // Slide switch: INPUT_PULLUP → LOW = ligado, HIGH = desligado
  bool switchAtual = (digitalRead(BTN_PIN) == LOW);

  // Detecta mudança e avisa o backend
  if (switchAtual != lastSwitchState) {
    lastSwitchState = switchAtual;
    String body = switchAtual ? "{\"switchOn\":true}" : "{\"switchOn\":false}";
    postJson(switchUrl, body);
    Serial.println(switchAtual ? "Switch: LIGADO" : "Switch: DESLIGADO");
    delay(200);
  }

  // Envia luminosidade e recebe estado do LED
  if (WiFi.status() == WL_CONNECTED) {
    int luminosidade = analogRead(LDR_PIN);
    Serial.print("Luminosidade: ");
    Serial.println(luminosidade);

    String payload = "{\"luminosity\":" + String(luminosidade) + "}";
    String resposta = postJsonResponse(sensorUrl, payload);

    if (resposta.length() > 0) {
      if (resposta.indexOf("true") >= 0) {
        digitalWrite(LED_PIN, HIGH);
      } else {
        digitalWrite(LED_PIN, LOW);
      }
    }
  }

  delay(3000);
}
