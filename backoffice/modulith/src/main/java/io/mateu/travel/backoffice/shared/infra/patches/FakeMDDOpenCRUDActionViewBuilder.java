package io.mateu.travel.backoffice.shared.infra.patches;

import io.mateu.mdd.core.app.MDDOpenCRUDAction;
import io.mateu.mdd.core.app.MDDOpenCRUDActionViewBuilder;
import io.mateu.mdd.core.interfaces.Crud;
import org.springframework.stereotype.Service;

@Service
public class FakeMDDOpenCRUDActionViewBuilder implements MDDOpenCRUDActionViewBuilder {
    @Override
    public Crud buildView(MDDOpenCRUDAction action) throws Exception {
        return null;
    }
}
