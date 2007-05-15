/*****************************************************************************
 * Copyright (c) 2006-2007, Cloudsmith Inc.
 * The code, documentation and other materials contained herein have been
 * licensed under the Eclipse Public License - v 1.0 by the copyright holder
 * listed above, as the Initial Contributor under such license. The text of
 * such license is available at www.eclipse.org.
 *****************************************************************************/

package org.eclipse.buckminster.jnlp;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Map;

import org.eclipse.buckminster.core.CorePlugin;
import org.eclipse.buckminster.core.helpers.BuckminsterException;
import org.eclipse.buckminster.core.mspec.builder.MaterializationSpecBuilder;
import org.eclipse.buckminster.core.mspec.model.MaterializationSpec;
import org.eclipse.buckminster.core.parser.IParser;
import org.eclipse.jface.dialogs.ProgressMonitorDialog;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.jface.wizard.Wizard;
import org.eclipse.jface.wizard.WizardPage;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.ImageData;
import org.eclipse.swt.widgets.Display;
import org.xml.sax.SAXException;

/**
 * @author Thomas Hallgren
 * 
 */
public class InstallWizard extends Wizard
{
	public static final String PROP_PROPVERSION = "propVersion";

	public static final String PROP_PROFILE_IMAGE_URL = "profileImageURL";

	public static final String PROP_PROFILE_TEXT = "profileText";

	public static final String PROP_ARTIFACT_URL = "artifactURL";

	public static final String PROP_ARTIFACT_TYPE = "artifactType";

	public static final String PROP_ARTIFACT_NAME = "artifactName";

	public static final String PROP_SPACE_NAME = "spaceName";

	public static final String PROP_WINDOW_TITLE = "windowTitle";
	
	public static final String PROP_WINDOW_ICON = "windowIcon";
	
	public static final String PROP_WIZARD_ICON = "wizardIcon";
	
	public static final String PROP_SPLASH_IMAGE = "splashImage";
	
	public static final String PROP_HELP_URL = "helpURL";
	
	public static final String[] MATERIALIZERS = {"filesystem", "workspace"};
	
	public static final String ARTIFACT_TYPE_MSPEC = "mspec";
	
	private Image m_brandingImage;
	
	private String m_brandingString;
	
	private String m_artifactName;
	
	public String m_windowTitle;
	
	public Image m_windowImage;
	
	public Image m_wizardImage;
	
	public Image m_splashImage;
	
	public String m_helpURL;
	
	private final MaterializationSpecBuilder m_builder = new MaterializationSpecBuilder();

	private final Map<String,String> m_properties;

	public InstallWizard(Map<String, String> properties) throws MissingPropertyException, IOException, SAXException
	{
		m_properties = properties;
		
		readProperties(properties);
				
		if(m_splashImage != null)
		{
			new SplashScreen(m_splashImage, 5);
		}

		// TODO Help
		//Program.launch("www.eclipse.org/buckminster");
		//setHelpAvailable(m_helpURL != null);
	}

	@Override
	public void addPages()
	{
		addPage(new StartPage());
		addPage(new DownloadLocationPage());
		addPage(new AdvancedSettingsPage());
		
		// TODO connection between wizard and server is not implemented yet
		//addPage(new RememberSettingsPage());
		// TODO replace with real spaces
		//addPage(new SpacePage(new String[] {"Space 1", "Space 2", "Space 3"}));
		
		setWindowTitle(m_windowTitle);

		if(m_windowImage != null)
		{
			getShell().setImage(m_windowImage);
		}
				
		if(m_wizardImage != null)
		{
			setDefaultPageImageDescriptor(ImageDescriptor.createFromImage(m_wizardImage));
		}
	}
	
	@Override
	public boolean performFinish()
	{
		((WizardPage)getContainer().getCurrentPage()).setErrorMessage(null);
		try
		{
			ProgressMonitorDialog dialog = new DownloadProgressMonitorDialog(getShell(), m_windowImage, m_windowTitle);
			dialog.run(true, true, new MaterializerRunnable(m_builder.createMaterializationSpec()));


			//getContainer().run(true, true, new MaterializerRunnable(m_builder.createMaterializationSpec()));
			return true;
		}
		catch(InterruptedException e)
		{
			((WizardPage)getContainer().getCurrentPage()).setErrorMessage("Operation cancelled");
		}
		catch(Exception e)
		{
			Throwable t = BuckminsterException.unwind(e);
			CorePlugin.getLogger().error(String.format("%s: %s", t.getClass(), t.getMessage()), t);
			((WizardPage)getContainer().getCurrentPage()).setErrorMessage(BuckminsterException.wrap(t).getMessage());
		}
		return false;
	}
	
	MaterializationSpecBuilder getMaterializationSpecBuilder()
	{
		return m_builder;
	}

	Map<String, String> getProperties()
	{
		return m_properties;
	}

	Image getBrandingImage()
	{
		return m_brandingImage;
	}

	String getBrandingString()
	{
		return m_brandingString;
	}
	
	String getArtifactName()
	{
		return m_artifactName;
	}

	@Override
	public String getWindowTitle()
	{
		return m_windowTitle;
	}
	
	Image getWindowImage()
	{
		return m_windowImage;
	}
	
	Image getWizardImage()
	{
		return m_wizardImage;
	}
	
	String getHelpURL()
	{
		return m_helpURL;
	}
	
	String[] getMaterializers()
	{
		return MATERIALIZERS;
	}
	
	private void readProperties(Map<String, String> properties) throws MissingPropertyException, IOException, SAXException
	{
		String tmp = properties.get(PROP_ARTIFACT_TYPE);
		if(tmp == null)
			throw new MissingPropertyException(PROP_ARTIFACT_TYPE);
		
		String artifactType = tmp;
		
		tmp = properties.get(PROP_ARTIFACT_URL);
		if(tmp == null)
			throw new MissingPropertyException(PROP_ARTIFACT_URL);

		if(ARTIFACT_TYPE_MSPEC.equals(artifactType))
		{
			URL url = new URL(tmp);
			InputStream stream = url.openStream();
			IParser<MaterializationSpec> parser = CorePlugin.getDefault().getParserFactory().getMaterializationSpecParser(
					true);
			
			m_builder.initFrom(parser.parse(ARTIFACT_TYPE_MSPEC, stream));
		} else
		{
			m_builder.setURL(new URL(tmp));
		}
		
		tmp = properties.get(PROP_ARTIFACT_NAME);
		if(tmp == null)
			throw new MissingPropertyException(PROP_ARTIFACT_NAME);
		m_builder.setName(tmp);
		m_artifactName = tmp;

		tmp = properties.get(PROP_PROFILE_IMAGE_URL);
		if(tmp == null)
		{
			m_brandingImage = null;
		}
		else
		{
			m_brandingImage = ImageDescriptor.createFromURL(new URL(tmp)).createImage();
		}

		tmp = properties.get(PROP_PROFILE_TEXT);
		if(tmp == null)
			throw new MissingPropertyException(PROP_PROFILE_TEXT);
		m_brandingString = tmp;

		tmp = properties.get(PROP_WINDOW_TITLE);
		if(tmp == null)
			throw new MissingPropertyException(PROP_WINDOW_TITLE);
		m_windowTitle = tmp;

		tmp = properties.get(PROP_WINDOW_ICON);
		if(tmp == null)
		{
			m_windowImage = null;
		}
		else
		{
			m_windowImage = ImageDescriptor.createFromURL(new URL(tmp)).createImage();
		}

		tmp = properties.get(PROP_WIZARD_ICON);
		if(tmp == null)
		{
			m_wizardImage = null;
		}
		else
		{
			m_wizardImage = getNormalizedWizardImage(ImageDescriptor.createFromURL(new URL(tmp)).createImage());
		}
		
		tmp = properties.get(PROP_SPLASH_IMAGE);
		if(tmp == null)
		{
			m_splashImage = null;
		}
		else
		{
			m_splashImage = ImageDescriptor.createFromURL(new URL(tmp)).createImage();
		}	
		
		m_helpURL = properties.get(PROP_HELP_URL);
	}		

	/**
	 * Wizard page doesn't display message text (the second line in title area) if the wizard image is too small
	 * This function creates a new image that is 64 pixels high - adds to the original image transparent stripe
	 * 
	 * @param origImage original image
	 * @return new image
	 */
	private Image getNormalizedWizardImage(Image origImage)
	{
		final int WIZARD_IMAGE_HEIGHT = 64;
		
		ImageData origImageData = origImage.getImageData();

		if(origImageData.height >= WIZARD_IMAGE_HEIGHT)
		{
			return origImage;
		}
		
		ImageData newImageData = new ImageData(origImageData.width, WIZARD_IMAGE_HEIGHT, origImageData.depth, origImageData.palette);
		
		newImageData.alpha = origImageData.alpha;
		//newImageData.bytesPerLine = origImageData.bytesPerLine;
		//newImageData.disposalMethod = origImageData.disposalMethod;
		//newImageData.scanlinePad = origImageData.scanlinePad;
		//newImageData.transparentPixel = origImageData.transparentPixel;
		//newImageData.maskData = origImageData.maskData;
		//newImageData.maskPad = origImageData.maskPad;
		//newImageData.type = origImageData.type;
        //newImageData.x = origImageData.x;
        //newImageData.y = origImageData.y;
		
		ImageData transparencyMask = origImageData.getTransparencyMask();
		boolean testTransparency = origImageData.getTransparencyType() == SWT.TRANSPARENCY_MASK || origImageData.getTransparencyType() == SWT.TRANSPARENCY_PIXEL;

		for(int y = 0; y < origImageData.height; y++)
		{
			for(int x = 0; x < origImageData.width; x++)
			{
				if(testTransparency && transparencyMask.getPixel(x, y) == 0)
				{
					newImageData.setAlpha(x, y, 0);
				}
				else
				{
					newImageData.setPixel(x, y, origImageData.getPixel(x, y));
					newImageData.setAlpha(x, y, origImageData.getAlpha(x, y));
				}
			}
		}

		for(int y = origImageData.height; y < WIZARD_IMAGE_HEIGHT; y++)
		{
			for(int x = 0; x < origImageData.width; x++)
			{
				newImageData.setAlpha(x, y, 0);
			}
		}

		return new Image(Display.getDefault(), newImageData);
	}

	@Override
	public void dispose()
	{
		if(m_brandingImage != null)
		{
			m_brandingImage.dispose();
		}
		
		if(m_windowImage != null)
		{
			m_windowImage.dispose();
		}
		
		if(m_wizardImage != null)
		{
			m_wizardImage.dispose();
		}
		
		if(m_splashImage != null)
		{
			m_splashImage.dispose();
		}
	}
}
