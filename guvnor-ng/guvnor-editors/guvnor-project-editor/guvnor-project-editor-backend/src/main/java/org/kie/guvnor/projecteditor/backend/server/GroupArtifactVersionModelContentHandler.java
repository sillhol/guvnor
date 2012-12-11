/*
 * Copyright 2012 JBoss Inc
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.kie.guvnor.projecteditor.backend.server;

import org.apache.maven.model.Model;
import org.apache.maven.model.io.xpp3.MavenXpp3Reader;
import org.apache.maven.model.io.xpp3.MavenXpp3Writer;
import org.codehaus.plexus.util.xml.pull.XmlPullParserException;
import org.kie.guvnor.projecteditor.model.GroupArtifactVersionModel;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

public class GroupArtifactVersionModelContentHandler {

    // TODO: Finish this when the core is more stable

    public String toString(GroupArtifactVersionModel gavModel) {

        MavenXpp3Writer writer = new MavenXpp3Writer();

        Model model = new Model();
        model.setGroupId(gavModel.getGroupId());
        model.setArtifactId(gavModel.getArtifactId());
        model.setVersion(gavModel.getVersion());

        StringWriter stringWriter = new StringWriter();
        try {
            writer.write(stringWriter, model);
        } catch (IOException e) {
            e.printStackTrace();  //TODO -Rikkola-
        }

        return stringWriter.toString();

//        return "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
//                "<project xmlns=\"http://maven.apache.org/POM/4.0.0\" xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\"\n" +
//                "         xsi:schemaLocation=\"http://maven.apache.org/POM/4.0.0 http://maven.apache.org/maven-v4_0_0.xsd\">\n" +
//                "  <groupId>org.kie.guvnor</groupId>\n" +
//                "  <artifactId>guvnor-parent</artifactId>\n" +
//                "  <name>Guvnor - Multi-project</name>\n" +
//                "  <version>6.0.0-SNAPSHOT</version>\n" +
//                "</project>";  // TODO -Rikkola-
    }

    public GroupArtifactVersionModel toModel(String propertiesString) {
        MavenXpp3Reader reader = new MavenXpp3Reader();
        Model model = null;
        try {
            model = reader.read(new StringReader(propertiesString));
        } catch (IOException e) {
            e.printStackTrace();  //TODO -Rikkola-
        } catch (XmlPullParserException e) {
            e.printStackTrace();  //TODO -Rikkola-
        }

        return new GroupArtifactVersionModel(
                model.getGroupId(),
                model.getArtifactId(),
                model.getVersion());
    }
}
