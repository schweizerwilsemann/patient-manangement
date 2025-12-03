package com.pm.analyticsservice.kafka;


import com.google.protobuf.InvalidProtocolBufferException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import patient.events.PatientEvent;

import jakarta.annotation.PostConstruct;

@Service
public class KafkaConsumer {

    private static final Logger log = LoggerFactory.getLogger(KafkaConsumer.class);

    @PostConstruct
    public void init() {
        log.info("KafkaConsumer initialized and ready to consume events.");
    }

    @KafkaListener(topics = "patient", groupId = "analytics-service", containerFactory = "kafkaListenerContainerFactory")
    public void consumeEvent(byte[] event) {
        try {
            PatientEvent patientEvent = PatientEvent.parseFrom(event);
            // ...perform any business related to analytics here

            log.info(
                    "Received Patient Event: [PatientId={}, PatientName={}, PatientEmail={}]",
                    patientEvent.getPatientId(), patientEvent.getName(), patientEvent.getEmail()
            );
      } catch (InvalidProtocolBufferException e) {
            log.error("Error deserializing event {}", e.getMessage());
        }
    }
            // ...perform any business related to analytics here

            log.info(
                    "Received Patient Event: [PatientId={}, PatientName={}, PatientEmail={}]",
                    patientEvent.getPatientId(), patientEvent.getName(), patientEvent.getEmail()
            );
      } catch (InvalidProtocolBufferException e) {
            log.error("Error deserializing event {}", e.getMessage());
        }
    }

}
