# Use Kafka and EmailConsumer to send Password Reset Email

## Requirements

Please refer document - https://www.digitalocean.com/community/tutorials/javamail-example-send-mail-in-java-smtp 
before solving this assignment

- You need to implement `sendEmail` method present inside KafkaEmailConsumer. 
   - This method will be subscribed to kafka at topic `password_reset`. 
   - The groupId for this service will be `emailService`.
   - Add Logic to set important Properties needed for sending email.
   - Leverage EmailSender to send Email.
- You also need to add logic in `sendEmail` method present in EmailUtil which will create Mime Message and have core logic for sending email.

## Hints
- Nothing is needed from your side in pom.xml or application.properties. Dependencies are already added.
- No new file need to be created.
- If you will try to run testcases without providing solution, all Testcases will fail.