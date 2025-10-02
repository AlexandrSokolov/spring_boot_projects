package com.savdev.demo.sb.service.multiple;

import com.savdev.demo.sb.api.InjectionWithQualifierDemoApi;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
@QualifierService4
public class InjectionWithQualifierCustomAnnotationService implements InjectionWithQualifierDemoApi {

  public static final String MSG = "InjectionWithQualifierService_4";

  @Override
  public String getMessage() {
    return MSG;
  }
}
