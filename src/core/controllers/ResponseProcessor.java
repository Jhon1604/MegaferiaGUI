package core.controllers;

import core.controllers.handlers.MessageHandler;
import core.controllers.utils.Response;
import java.awt.Component;
import java.util.function.Function;

/**
 * Procesa respuestas y ejecuta acciones asociadas
 */
public class ResponseProcessor {

    public static final class ProcessingResult<T> {

        private final boolean success;
        private final T data;

        public ProcessingResult(boolean success, T data) {
            this.success = success;
            this.data = data;
        }

        public boolean isSuccess() {
            return success;
        }

        public T getData() {
            return data;
        }
    }

    private final MessageHandler messageHandler;

    public ResponseProcessor(MessageHandler messageHandler) {
        this.messageHandler = messageHandler;
    }

    public boolean handle(Component parent, Response response, boolean notifySuccess) {
        return messageHandler.handleResponse(parent, response, notifySuccess);
    }

    public <T> ProcessingResult<T> process(Component parent, Response response, boolean notifySuccess, Function<Response, T> extractor) {
        boolean success = handle(parent, response, notifySuccess);
        T data = null;
        if (success && extractor != null) {
            data = extractor.apply(response);
        }
        return new ProcessingResult<>(success, data);
    }

    public boolean handleAndRun(Component parent, Response response, boolean notifySuccess, Runnable onSuccess) {
        boolean success = handle(parent, response, notifySuccess);
        if (success && onSuccess != null) {
            onSuccess.run();
        }
        return success;
    }
}

