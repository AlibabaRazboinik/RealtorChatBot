package org.example.speach;

import org.apache.commons.io.FileUtils;
import org.telegram.telegrambots.api.methods.GetFile;
import org.telegram.telegrambots.api.objects.Message;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.DefaultAbsSender;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import com.google.cloud.speech.v1.RecognitionAudio;
import com.google.cloud.speech.v1.RecognitionConfig;
import com.google.cloud.speech.v1.RecognizeRequest;
import com.google.cloud.speech.v1.RecognizeResponse;
import com.google.cloud.speech.v1.SpeechClient;
import com.google.cloud.speech.v1.SpeechRecognitionAlternative;
import com.google.cloud.speech.v1.SpeechRecognitionResult;
import com.google.protobuf.ByteString;

import java.io.File;

public class VoiceMessageHandler {
    private final DefaultAbsSender sender;
    private String transcription;

    public VoiceMessageHandler (DefaultAbsSender sender) {
        this.sender = sender;
        this.transcription = "";
    }

    public String getTranscription(Update update) {
        try (SpeechClient speechClient = SpeechClient.create()){
            String langCode = "ru-RU";
            int sampleRateHertz = 16000;

            RecognitionConfig.AudioEncoding encoding = RecognitionConfig.AudioEncoding.OGG_OPUS;
            RecognitionConfig config =
                    RecognitionConfig.newBuilder()
                            .setLanguageCode(langCode)
                            .setSampleRateHertz(sampleRateHertz)
                            .setEncoding(encoding)
                            .build();

            byte[] data = FileUtils.readFileToByteArray(downloadUserVoiceMsg(update));;

            ByteString content = ByteString.copyFrom(data);
            RecognitionAudio audio = RecognitionAudio.newBuilder().setContent(content).build();
            RecognizeRequest request =
                    RecognizeRequest.newBuilder().setConfig(config).setAudio(audio).build();
            RecognizeResponse response = speechClient.recognize(request);

            for (SpeechRecognitionResult result : response.getResultsList()) {
                // First alternative is the most probable result
                SpeechRecognitionAlternative alternative = result.getAlternativesList().get(0);
                transcription = alternative.getTranscript();
            }
        } catch (Exception e) {}

        return transcription;
    }

    /**
     * Get user's voice message file_id.
     * @param update
     * @return
     */
    private String getUserVoiceMsgFileId(Update update) {
        Message message = update.getMessage();
        return message.getVoice().getFileId();
    }

    private File downloadUserVoiceMsg(Update update) throws TelegramApiException {
        String fileId = getUserVoiceMsgFileId(update);
        GetFile getFile = new GetFile().setFileId(fileId);
        String filePath = sender.execute(getFile).getFilePath();

        return sender.downloadFile(filePath);
    }

}
