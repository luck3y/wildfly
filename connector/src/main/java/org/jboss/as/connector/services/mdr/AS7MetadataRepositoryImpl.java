/*
 * JBoss, Home of Professional Open Source.
 * Copyright 2011, Red Hat, Inc., and individual contributors
 * as indicated by the @author tags. See the copyright.txt file in the
 * distribution for a full listing of individual contributors.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 2.1 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 */

package org.jboss.as.connector.services.mdr;

import java.io.File;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.jboss.jca.common.api.metadata.ironjacamar.IronJacamar;
import org.jboss.jca.common.api.metadata.ra.Connector;
import org.jboss.jca.core.mdr.SimpleMetadataRepository;
import org.jboss.jca.core.spi.mdr.AlreadyExistsException;
import org.jboss.jca.core.spi.mdr.NotFoundException;
import org.jboss.msc.service.ServiceName;

/**
 * An AS7' implementation of MetadataRepository
 *
 * @author Stefano Maestri (c) 2011 Red Hat Inc.
 */
public class AS7MetadataRepositoryImpl extends SimpleMetadataRepository implements AS7MetadataRepository {

    private final Map<String, IronJacamar> ironJacamarMetaData = new HashMap<String, IronJacamar>();
    private final List<ServiceName> jndiServices = new LinkedList<ServiceName>();

    /**
     * Constructor
     */
    public AS7MetadataRepositoryImpl() {
    }

    @Override
    public synchronized void registerResourceAdapter(String uniqueId, File root, Connector md, IronJacamar ijmd)
        throws AlreadyExistsException {
        super.registerResourceAdapter(uniqueId, root, md, ijmd);
        if (ijmd != null) {
            ironJacamarMetaData.put(uniqueId, ijmd);
        }
    }

    @Override
    public synchronized void unregisterResourceAdapter(String uniqueId) throws NotFoundException {
        super.unregisterResourceAdapter(uniqueId);
    }

    @Override
    public synchronized boolean hasResourceAdapter(String uniqueId) {
        return super.hasResourceAdapter(uniqueId);
    }

    @Override
    public synchronized Connector getResourceAdapter(String uniqueId) throws NotFoundException {
        return super.getResourceAdapter(uniqueId);
    }

    @Override
    public synchronized Set<String> getResourceAdapters() {
        return super.getResourceAdapters();
    }

    @Override
    public synchronized File getRoot(String uniqueId) throws NotFoundException {
        return super.getRoot(uniqueId);
    }

    @Override
    public synchronized IronJacamar getIronJacamar(String uniqueId) throws NotFoundException {
        return super.getIronJacamar(uniqueId);
    }

    @Override
    public synchronized void registerJndiMapping(String uniqueId, String clz, String jndi) {
        super.registerJndiMapping(uniqueId, clz, jndi);
    }

    @Override
    public synchronized void unregisterJndiMapping(String uniqueId, String clz, String jndi) throws NotFoundException {
        super.unregisterJndiMapping(uniqueId, clz, jndi);
    }

    @Override
    public synchronized boolean hasJndiMappings(String uniqueId) {
        return super.hasJndiMappings(uniqueId);
    }

    @Override
    public synchronized Map<String, List<String>> getJndiMappings(String uniqueId) throws NotFoundException {
        return super.getJndiMappings(uniqueId);
    }

    @Override
    public synchronized IronJacamar getIronJcamarMetaData(String uniqueId) {
        return ironJacamarMetaData.get(uniqueId);
    }

    @Override
    public synchronized Set<String> getResourceAdaptersWithIronJacamarMetadata() {
        return ironJacamarMetaData.keySet();
    }
}
