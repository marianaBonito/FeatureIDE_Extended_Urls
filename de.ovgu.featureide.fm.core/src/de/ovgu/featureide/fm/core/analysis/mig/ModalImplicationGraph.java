/* FeatureIDE - A Framework for Feature-Oriented Software Development
 * Copyright (C) 2005-2019  FeatureIDE team, University of Magdeburg, Germany
 *
 * This file is part of FeatureIDE.
 *
 * FeatureIDE is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Lesser General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * FeatureIDE is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with FeatureIDE.  If not, see <http://www.gnu.org/licenses/>.
 *
 * See http://featureide.cs.ovgu.de/ for further information.
 */
package de.ovgu.featureide.fm.core.analysis.mig;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import de.ovgu.featureide.fm.core.analysis.cnf.CNF;
import de.ovgu.featureide.fm.core.analysis.cnf.LiteralSet;
import de.ovgu.featureide.fm.core.analysis.cnf.solver.RuntimeContradictionException;
import de.ovgu.featureide.fm.core.job.LongRunningWrapper;

/**
 * Adjacency list implementation for a feature graph.
 *
 * @author Sebastian Krieter
 */
public class ModalImplicationGraph implements IEdgeTypes, Serializable {

	private static final long serialVersionUID = 5258868675944962032L;

	public static ModalImplicationGraph build(CNF satInstance, boolean detectStrong) {
		return LongRunningWrapper.runMethod(new MIGBuilder(satInstance, detectStrong));
	}

	final List<Vertex> adjList;
	final List<LiteralSet> complexClauses = new ArrayList<>(0);

	public ModalImplicationGraph() {
		adjList = new ArrayList<>(0);
	}

	public ModalImplicationGraph(int numVariables) {
		adjList = new ArrayList<>(numVariables);
	}

	public void copyValues(ModalImplicationGraph other) {
		adjList.addAll(other.adjList);
		complexClauses.addAll(other.complexClauses);
	}

	public Traverser traverse() {
		return new Traverser(this);
	}

	public Vertex getVertex(int literal) {
		return adjList.get(((Math.abs(literal) - 1) << 1) + (literal < 0 ? 0 : 1));
	}

	public List<Vertex> getAdjList() {
		return Collections.unmodifiableList(adjList);
	}

	public List<LiteralSet> getComplexClauses() {
		return Collections.unmodifiableList(complexClauses);
	}

	public void addClause(LiteralSet clause) {
		final int[] literals = clause.getLiterals();
		switch (clause.size()) {
		case 0:
			throw new RuntimeContradictionException();
		case 1: {
			final int literal = literals[0];
			final Vertex vertex = getVertex(literal);
			vertex.setCore(literal > 0);
			vertex.setDead(literal < 0);
			break;
		}
		case 2: {
			final Vertex vertex0 = getVertex(-literals[0]);
			final Vertex vertex1 = getVertex(-literals[1]);
			addStrongEdge(vertex0, -vertex1.getVar());
			addStrongEdge(vertex1, -vertex0.getVar());
			break;
		}
		default: {
			final int newClauseIndex = complexClauses.size();
			complexClauses.add(clause);
			for (final int literal : literals) {
				addWeakEdge(getVertex(-literal), newClauseIndex);
			}
			break;
		}
		}
	}

	public void removeClause(LiteralSet clause) {
		final int[] literals = clause.getLiterals();
		switch (clause.size()) {
		case 0:
			throw new RuntimeContradictionException();
		case 1: {
			final int literal = literals[0];
			final Vertex vertex = getVertex(literal);
			vertex.setCore(literal > 0);
			vertex.setDead(literal < 0);
			break;
		}
		case 2: {
			final Vertex vertex0 = getVertex(-literals[0]);
			final Vertex vertex1 = getVertex(-literals[1]);
			addStrongEdge(vertex0, -vertex1.getVar());
			addStrongEdge(vertex1, -vertex0.getVar());
			break;
		}
		default: {
			final int newClauseIndex = complexClauses.size();
			complexClauses.add(clause);
			for (final int literal : literals) {
				addWeakEdge(getVertex(-literal), newClauseIndex);
			}
			break;
		}
		}
	}

	private void addWeakEdge(final Vertex vertex, final int index) {
		final int[] oldComplexClauses = vertex.getComplexClauses();
		final int[] newComplexClauses = Arrays.copyOf(oldComplexClauses, oldComplexClauses.length + 1);
		newComplexClauses[oldComplexClauses.length] = index;
		vertex.setComplexClauses(newComplexClauses);
	}

	private void addStrongEdge(final Vertex vertex, final int edge) {
		final int[] oldStrongEdges = vertex.getStrongEdges();
		final int[] newStrongEdges = Arrays.copyOf(oldStrongEdges, oldStrongEdges.length + 1);
		newStrongEdges[oldStrongEdges.length] = edge;
		vertex.setStrongEdges(newStrongEdges);
	}

}
