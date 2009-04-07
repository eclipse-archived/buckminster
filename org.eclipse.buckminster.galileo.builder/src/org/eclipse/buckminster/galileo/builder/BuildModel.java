package org.eclipse.buckminster.galileo.builder;

import java.util.Collections;
import java.util.List;

import org.eclipse.core.runtime.CoreException;
import org.eclipse.equinox.internal.provisional.p2.core.Version;
import org.w3c.dom.Element;

@SuppressWarnings("restriction")
public class BuildModel extends ElementBased
{
	public static class Category extends ElementBased
	{
		public Category(Element element)
		{
			super(element);
		}

		public String getDescription()
		{
			return getAttribute("description"); //$NON-NLS-1$
		}

		public String getLabel()
		{
			return getAttribute("label"); //$NON-NLS-1$
		}

		public String getName()
		{
			return getAttribute("name"); //$NON-NLS-1$
		}
	}

	public static class Configuration extends ElementBased
	{
		public Configuration(Element element)
		{
			super(element);
		}

		public String getArch()
		{
			return getAttribute("arch"); //$NON-NLS-1$
		}

		public String getOS()
		{
			return getAttribute("os"); //$NON-NLS-1$
		}

		public String getWS()
		{
			return getAttribute("ws"); //$NON-NLS-1$
		}
	}

	public static class Contribution extends ElementBased
	{
		public Contribution(Element element)
		{
			super(element);
		}

		public List<Feature> getFeatures() throws CoreException
		{
			return createElementBasedList(Feature.class, "features"); //$NON-NLS-1$
		}

		public List<Repository> getRepositories() throws CoreException
		{
			return createElementBasedList(Repository.class, "repositories"); //$NON-NLS-1$
		}
	}

	public static class Feature extends ElementBased
	{
		public Feature(Element element)
		{
			super(element);
		}

		public List<Category> getCategories(List<Category> allCategories) throws CoreException
		{
			// We cannot really resolve the links so we just use brute force

			String category = getAttribute("category"); // Shouldn't this be plural? //$NON-NLS-1$
			if(category != null)
			{
				int lastDot = category.lastIndexOf('.');
				if(lastDot >= 0)
				{
					try
					{
						int index = Integer.parseInt(category.substring(lastDot + 1));
						if(index >= 0)
						{
							if(index < allCategories.size())
								return Collections.singletonList(allCategories.get(index));
						}
					}
					catch(NumberFormatException e)
					{
					}
				}
			}
			return Collections.emptyList();
		}

		public String getId()
		{
			return getAttribute("id"); //$NON-NLS-1$
		}

		public Version getVersion()
		{
			return Version.parseVersion(getAttribute("version")); //$NON-NLS-1$
		}
	}

	public static class Promotion extends ElementBased
	{
		public Promotion(Element element)
		{
			super(element);
		}

		public String getBaseURL()
		{
			return getAttribute("baseURL"); //$NON-NLS-1$
		}

		public String getBuildAlias()
		{
			return getAttribute("buildAlias"); //$NON-NLS-1$
		}

		public String getDownloadDirectory()
		{
			return getAttribute("downloadDirectory"); //$NON-NLS-1$
		}

		public String getUploadDirectory()
		{
			return getAttribute("uploadDirectory"); //$NON-NLS-1$
		}
	}

	public static class Repository extends ElementBased
	{
		public Repository(Element element)
		{
			super(element);
		}

		public String getLocation()
		{
			return getAttribute("location"); //$NON-NLS-1$
		}
	}

	public BuildModel(Element element) throws CoreException
	{
		super(element);
	}

	public List<Category> getCategories() throws CoreException
	{
		return createElementBasedList(Category.class, "categories"); //$NON-NLS-1$
	}

	public List<Configuration> getConfigurations() throws CoreException
	{
		return createElementBasedList(Configuration.class, "configurations"); //$NON-NLS-1$
	}

	public List<Contribution> getContributions() throws CoreException
	{
		return createElementBasedList(Contribution.class, "contributions"); //$NON-NLS-1$
	}

	public Promotion getPromotion() throws CoreException
	{
		return createElementBasedSingleton(Promotion.class, "promotion"); //$NON-NLS-1$
	}
}
