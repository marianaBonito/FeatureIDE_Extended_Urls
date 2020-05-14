package de.ovgu.featureide.fm.attributes.statistics;

import java.nio.file.Paths;
import java.util.List;

import org.eclipse.core.resources.IFile;
import org.eclipse.jface.action.IMenuManager;
import org.eclipse.jface.action.IToolBarManager;
import org.eclipse.jface.viewers.SelectionChangedEvent;
import org.eclipse.jface.viewers.TreeExpansionEvent;
import org.eclipse.jface.viewers.TreeViewer;
import org.eclipse.ui.IEditorPart;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPage;
import org.eclipse.ui.IWorkbenchWindow;
import org.eclipse.ui.PlatformUI;

import de.ovgu.featureide.fm.attributes.config.ExtendedConfiguration;
import de.ovgu.featureide.fm.core.configuration.Configuration;
import de.ovgu.featureide.fm.core.io.manager.ConfigurationManager;
import de.ovgu.featureide.fm.ui.editors.configuration.ConfigurationEditor;
import de.ovgu.featureide.fm.ui.views.outline.custom.OutlineLabelProvider;
import de.ovgu.featureide.fm.ui.views.outline.custom.OutlineProvider;
import de.ovgu.featureide.fm.ui.views.outline.custom.OutlineTreeContentProvider;
import de.ovgu.featureide.fm.ui.views.outline.custom.filters.IOutlineFilter;

public class ExtendedFMOutlineProvider extends OutlineProvider {

	Configuration config;

	public ExtendedFMOutlineProvider() {
		super(new ExtendedFMTreeContentProvider(), new ExtendedFMLabelProvider());
	}

	public ExtendedFMOutlineProvider(OutlineTreeContentProvider treeProvider, OutlineLabelProvider labelProvider) {
		super(treeProvider, labelProvider);
	}

	@Override
	public void selectionChanged(SelectionChangedEvent event) {

	}

	@Override
	public void treeCollapsed(TreeExpansionEvent event) {

	}

	@Override
	public void treeExpanded(TreeExpansionEvent event) {

	}

	@Override
	protected void initContextMenuActions(IMenuManager manager) {

	}

	@Override
	protected void initToolbarActions(IToolBarManager manager) {

	}

	@Override
	protected List<IOutlineFilter> getFilters() {
		return null;
	}

	@Override
	public boolean isSupported(IEditorPart part, IFile file) {
		try {
			if (ConfigurationManager.getInstance(Paths.get(file.getLocationURI())) != null) {
				Configuration conf = ConfigurationManager.getInstance(Paths.get(file.getLocationURI())).getObject();
				return conf instanceof ExtendedConfiguration;
			}
		} catch (ClassCastException e) {}
		return false;
	}

	@Override
	public void handleUpdate(TreeViewer viewer, IFile iFile) {

		final IWorkbench workbench = PlatformUI.getWorkbench();
		final IWorkbenchWindow window = workbench.getActiveWorkbenchWindow();
		final IWorkbenchPage page = window.getActivePage();
		final IEditorPart activeEditor = page.getActiveEditor();
		if (activeEditor instanceof ConfigurationEditor) {
			final ConfigurationEditor confEditor = (ConfigurationEditor) activeEditor;
			config = confEditor.getConfigurationManager().getObject();

			getTreeProvider().inputChanged(viewer, null, config);
		}

	}

}
