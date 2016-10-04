/*
 * Copyright 2015-2016 Red Hat, Inc. and/or its affiliates
 * and other contributors as indicated by the @author tags.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.hawkular.apm.instrumenter.rules;

import org.hawkular.apm.api.model.config.instrumentation.jvm.InstrumentAction;
import org.hawkular.apm.api.model.config.instrumentation.jvm.SetState;

/**
 * This class transforms the SetDetail action type.
 *
 * @author gbrown
 */
public class SetStateTransformer implements InstrumentActionTransformer {

    @Override
    public Class<? extends InstrumentAction> getActionType() {
        return SetState.class;
    }

    @Override
    public String convertToRuleAction(InstrumentAction action) {
        SetState setState = (SetState) action;
        StringBuilder builder = new StringBuilder(32);

        builder.append("setState(");

        if (setState.getContextExpression() == null) {
            builder.append("null");
        } else {
            builder.append(setState.getContextExpression());
        }

        builder.append(",\"");

        builder.append(setState.getName());

        builder.append("\",");

        if (setState.getValueExpression() == null) {
            builder.append("null");
        } else {
            builder.append(setState.getValueExpression());
        }

        builder.append(",");

        builder.append(setState.isSession());

        builder.append(")");

        return builder.toString();
    }

}
