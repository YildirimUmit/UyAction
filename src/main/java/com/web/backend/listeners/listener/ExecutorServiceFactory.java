package com.web.backend.listeners.listener;

import java.util.concurrent.*;

public interface ExecutorServiceFactory {
    ExecutorService getExecutorService();
}
