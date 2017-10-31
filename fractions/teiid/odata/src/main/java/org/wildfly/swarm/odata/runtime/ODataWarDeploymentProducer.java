/**
 * Copyright 2015-2016 Red Hat, Inc, and individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.wildfly.swarm.odata.runtime;

import static org.wildfly.swarm.spi.api.Defaultable.string;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Any;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;

import org.jboss.shrinkwrap.api.Archive;
import org.wildfly.swarm.odata.ODataFraction;
import org.wildfly.swarm.spi.api.ArtifactLookup;
import org.wildfly.swarm.spi.api.Defaultable;
import org.wildfly.swarm.spi.api.annotations.Configurable;
import org.wildfly.swarm.undertow.WARArchive;

@ApplicationScoped
public class ODataWarDeploymentProducer {

    public static final String DEPLOYMENT_GAV = "org.jboss.teiid:teiid-olingo:war:odata";

    public static final String DEPLOYMENT_NAME = "odata.war";

    @Inject
    @Any
    ODataFraction fraction;

    @Inject
    ArtifactLookup lookup;

    @Configurable("swarm.teiid.odata.context")
    private Defaultable<String> context = string("/");

    @Produces
    public Archive odataWar() throws Exception {

        Archive deployment = this.lookup.artifact(DEPLOYMENT_GAV, DEPLOYMENT_NAME);

        deployment.as(WARArchive.class).setContextRoot(this.context.get());

        return deployment;
    }
}
