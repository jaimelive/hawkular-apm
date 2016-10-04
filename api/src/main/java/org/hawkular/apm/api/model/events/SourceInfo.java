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
package org.hawkular.apm.api.model.events;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.HashSet;
import java.util.Set;

import org.hawkular.apm.api.model.Property;
import org.hawkular.apm.api.utils.SerializationUtil;

/**
 * This class represents information cached about a source node, to enable it to be
 * correlated to a consumer.
 *
 * @author gbrown
 */
public class SourceInfo implements Externalizable, ApmEvent {

    private String id;

    private EndpointRef endpoint;

    private long timestamp = 0;

    private long duration = 0;

    private String fragmentId;

    private String hostName;

    private String hostAddress;

    private boolean multipleConsumers = false;

    private Set<Property> properties = new HashSet<Property>();

    /**
     * The default constructor.
     */
    public SourceInfo() {
    }

    /**
     * The copy constructor.
     *
     * Note: This operation just references the same properties, to avoid any
     * unnecessary object creation and copying.
     */
    public SourceInfo(SourceInfo si) {
        this.id = si.id;
        this.endpoint = si.endpoint;
        this.timestamp = si.timestamp;
        this.duration = si.duration;
        this.fragmentId = si.fragmentId;
        this.hostName = si.hostName;
        this.hostAddress = si.hostAddress;
        this.multipleConsumers = si.multipleConsumers;
        this.properties = si.properties;  // Don't copy, just reference
    }

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the endpoint
     */
    public EndpointRef getEndpoint() {
        return endpoint;
    }

    /**
     * @param endpoint the endpoint to set
     */
    public void setEndpoint(EndpointRef endpoint) {
        this.endpoint = endpoint;
    }

    /**
     * @return the timestamp
     */
    public long getTimestamp() {
        return timestamp;
    }

    /**
     * @param timestamp the timestamp to set
     */
    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }

    /**
     * @return the duration
     */
    public long getDuration() {
        return duration;
    }

    /**
     * @param duration the duration to set
     */
    public void setDuration(long duration) {
        this.duration = duration;
    }

    /**
     * @return the fragmentId
     */
    public String getFragmentId() {
        return fragmentId;
    }

    /**
     * @param fragmentId the fragmentId to set
     */
    public void setFragmentId(String fragmentId) {
        this.fragmentId = fragmentId;
    }

    /**
     * @return the hostName
     */
    public String getHostName() {
        return hostName;
    }

    /**
     * @param hostName the hostName to set
     */
    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    /**
     * @return the hostAddress
     */
    public String getHostAddress() {
        return hostAddress;
    }

    /**
     * @param hostAddress the hostAddress to set
     */
    public void setHostAddress(String hostAddress) {
        this.hostAddress = hostAddress;
    }

    /**
     * @return the multipleConsumers
     */
    public boolean isMultipleConsumers() {
        return multipleConsumers;
    }

    /**
     * @param multipleConsumers the multipleConsumers to set
     */
    public void setMultipleConsumers(boolean multipleConsumers) {
        this.multipleConsumers = multipleConsumers;
    }

    /**
     * @return the properties
     */
    public Set<Property> getProperties() {
        return properties;
    }

    /**
     * @param properties the properties to set
     */
    public void setProperties(Set<Property> properties) {
        this.properties = properties;
    }

    /**
     * This method determines whether there is atleast one
     * property with the supplied name.
     *
     * @param name The property name
     * @return Whether a property of the supplied name is defined
     */
    public boolean hasProperty(String name) {
        for (Property property : this.properties) {
            if (property.getName().equals(name)) {
                return true;
            }
        }
        return false;
    }

    /**
     * This method returns the set of properties having the
     * supplied property name.
     *
     * @param name The property name
     * @return The set of properties with the supplied name
     */
    public Set<Property> getProperties(String name) {
        Set<Property> ret = new HashSet<Property>();
        for (Property property : this.properties) {
            if (property.getName().equals(name)) {
                ret.add(property);
            }
        }
        return ret;
    }

    @Override
    public String toString() {
        return "SourceInfo [id=" + id + ", endpoint=" + endpoint + ", timestamp=" + timestamp + ", duration="
                + duration + ", fragmentId=" + fragmentId + ", hostName=" + hostName + ", hostAddress=" + hostAddress
                + ", multipleConsumers=" + multipleConsumers + ", properties=" + properties + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (duration ^ (duration >>> 32));
        result = prime * result + ((endpoint == null) ? 0 : endpoint.hashCode());
        result = prime * result + ((fragmentId == null) ? 0 : fragmentId.hashCode());
        result = prime * result + ((hostAddress == null) ? 0 : hostAddress.hashCode());
        result = prime * result + ((hostName == null) ? 0 : hostName.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + (multipleConsumers ? 1231 : 1237);
        result = prime * result + ((properties == null) ? 0 : properties.hashCode());
        result = prime * result + (int) (timestamp ^ (timestamp >>> 32));
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        SourceInfo other = (SourceInfo) obj;
        if (duration != other.duration)
            return false;
        if (endpoint == null) {
            if (other.endpoint != null)
                return false;
        } else if (!endpoint.equals(other.endpoint))
            return false;
        if (fragmentId == null) {
            if (other.fragmentId != null)
                return false;
        } else if (!fragmentId.equals(other.fragmentId))
            return false;
        if (hostAddress == null) {
            if (other.hostAddress != null)
                return false;
        } else if (!hostAddress.equals(other.hostAddress))
            return false;
        if (hostName == null) {
            if (other.hostName != null)
                return false;
        } else if (!hostName.equals(other.hostName))
            return false;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (multipleConsumers != other.multipleConsumers)
            return false;
        if (properties == null) {
            if (other.properties != null)
                return false;
        } else if (!properties.equals(other.properties))
            return false;
        if (timestamp != other.timestamp)
            return false;
        return true;
    }

    @Override
    public void readExternal(ObjectInput ois) throws IOException, ClassNotFoundException {
        ois.readInt(); // Read version

        id = SerializationUtil.deserializeString(ois);
        endpoint = (EndpointRef)ois.readObject();
        timestamp = ois.readLong();
        duration = ois.readLong();
        fragmentId = SerializationUtil.deserializeString(ois);
        hostName = SerializationUtil.deserializeString(ois);
        hostAddress = SerializationUtil.deserializeString(ois);
        multipleConsumers = ois.readBoolean();

        int size = ois.readInt();
        for (int i = 0; i < size; i++) {
            properties.add((Property) ois.readObject());
        }
    }

    @Override
    public void writeExternal(ObjectOutput oos) throws IOException {
        oos.writeInt(1); // Write version

        SerializationUtil.serializeString(oos, id);
        oos.writeObject(endpoint);
        oos.writeLong(timestamp);
        oos.writeLong(duration);
        SerializationUtil.serializeString(oos, fragmentId);
        SerializationUtil.serializeString(oos, hostName);
        SerializationUtil.serializeString(oos, hostAddress);
        oos.writeBoolean(multipleConsumers);

        oos.writeInt(properties.size());
        for (Property property : properties) {
            oos.writeObject(property);
        }
    }

}
