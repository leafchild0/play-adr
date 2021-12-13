package com.leafchild0.adr.common;

import com.typesafe.config.Config;
import play.Environment;
import play.api.OptionalSourceMapper;
import play.api.routing.Router;
import play.http.DefaultHttpErrorHandler;
import play.mvc.Http.RequestHeader;
import play.mvc.Result;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.inject.Singleton;
import java.util.concurrent.CompletionStage;
import java.util.function.BiFunction;

/**
 * Main Error handler for API
 *
 * @author vmalyshev
 * @date 13.12.2021
 */

@Singleton
public class ErrorHandler extends DefaultHttpErrorHandler {

    @Inject
    public ErrorHandler(
            Config config,
            Environment environment,
            OptionalSourceMapper sourceMapper,
            Provider<Router> routes) {
        super(config, environment, sourceMapper, routes);
    }

    @Override
    public CompletionStage<Result> onServerError(RequestHeader request, Throwable exception) {
        // don't replace to super::onProdServerError because of https://bugs.openjdk.java.net/browse/JDK-8139836
        return exceptionInterception(request, exception, (r, e) -> super.onServerError(r, e));
    }

    private CompletionStage<Result> exceptionInterception(RequestHeader request, Throwable exception,
                                                          BiFunction<RequestHeader, Throwable, CompletionStage<Result>> defaultBehavior) {
        return defaultBehavior.apply(request, exception);
    }
}
