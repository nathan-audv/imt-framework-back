package imt.framework.back.imtframeworkback.core.utils;

public interface UseCase<Request, Response> {
    public Response command(Request request);
}
