/*
 * Catroid: An on-device visual programming system for Android devices
 * Copyright (C) 2010-2016 The Catrobat Team
 * (<http://developer.catrobat.org/credits>)
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as
 * published by the Free Software Foundation, either version 3 of the
 * License, or (at your option) any later version.
 *
 * An additional term exception under section 7 of the GNU Affero
 * General Public License, version 3, is available at
 * http://developer.catrobat.org/license_additional_term
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */

package org.catrobat.catroid.uitest.content.brick;

import android.widget.ListView;

import org.catrobat.catroid.ProjectManager;
import org.catrobat.catroid.R;
import org.catrobat.catroid.content.Project;
import org.catrobat.catroid.content.Script;
import org.catrobat.catroid.content.Sprite;
import org.catrobat.catroid.content.StartScript;
import org.catrobat.catroid.content.bricks.Brick;
import org.catrobat.catroid.content.bricks.LegoEv3PlayToneBrick;
import org.catrobat.catroid.ui.ScriptActivity;
import org.catrobat.catroid.ui.adapter.BrickAdapter;
import org.catrobat.catroid.uitest.util.BaseActivityInstrumentationTestCase;
import org.catrobat.catroid.uitest.util.UiTestUtils;

import java.util.ArrayList;

public class LegoEv3PlayToneBrickTest extends BaseActivityInstrumentationTestCase<ScriptActivity> {

	private static final double INITIAL_DURATION = 1.3;
	private static final double SET_DURATION = 0.2;
	private static final int INITIAL_FREQUENCY = 20;
	private static final int SET_FREQUENCY = 100;
	private static final int INITIAL_VOLUME = 99;
	private static final int SET_VOLUME = 22;

	private Project project;
	private LegoEv3PlayToneBrick playToneBrick;

	public LegoEv3PlayToneBrickTest() {
		super(ScriptActivity.class);
	}

	@Override
	public void setUp() throws Exception {
		// normally super.setUp should be called first
		// but kept the test failing due to view is null
		// when starting in ScriptActivity
		createProject();
		super.setUp();
	}

	public void testEv3PlayToneBrick() {
		ListView dragDropListView = UiTestUtils.getScriptListView(solo);
		BrickAdapter adapter = (BrickAdapter) dragDropListView.getAdapter();

		int childrenCount = adapter.getChildCountFromLastGroup();
		int groupCount = adapter.getScriptCount();

		assertEquals("Incorrect number of bricks.", 2, dragDropListView.getChildCount());
		assertEquals("Incorrect number of bricks.", 1, childrenCount);

		ArrayList<Brick> projectBrickList = project.getSceneList().get(0).getSpriteList().get(0).getScript(0)
				.getBrickList();
		assertEquals("Incorrect number of bricks.", 1, projectBrickList.size());

		assertEquals("Wrong Brick instance.", projectBrickList.get(0), adapter.getChild(groupCount - 1, 0));
		assertNotNull("TextView does not exist.", solo.getText(solo.getString(R.string.ev3_play_tone)));

		UiTestUtils.testBrickWithFormulaEditor(solo, ProjectManager.getInstance().getCurrentSprite(),
				R.id.brick_ev3_tone_duration_edit_text, SET_DURATION, Brick.BrickField.LEGO_EV3_DURATION_IN_SECONDS, playToneBrick);

		UiTestUtils.testBrickWithFormulaEditor(solo, ProjectManager.getInstance().getCurrentSprite(),
				R.id.brick_ev3_tone_freq_edit_text, SET_FREQUENCY, Brick.BrickField.LEGO_EV3_FREQUENCY, playToneBrick);

		UiTestUtils.testBrickWithFormulaEditor(solo, ProjectManager.getInstance().getCurrentSprite(),
				R.id.brick_ev3_tone_volume_edit_text, SET_VOLUME, Brick.BrickField.LEGO_EV3_VOLUME, playToneBrick);
	}

	private void createProject() {
		project = new Project(null, UiTestUtils.DEFAULT_TEST_PROJECT_NAME);
		Sprite sprite = new Sprite("cat");
		Script script = new StartScript();

		playToneBrick = new LegoEv3PlayToneBrick(INITIAL_FREQUENCY, (float) INITIAL_DURATION, INITIAL_VOLUME);

		script.addBrick(playToneBrick);

		sprite.addScript(script);
		project.getSceneList().get(0).addSprite(sprite);

		ProjectManager.getInstance().setProject(project);
		ProjectManager.getInstance().setCurrentSprite(sprite);
		ProjectManager.getInstance().setCurrentScript(script);
	}
}