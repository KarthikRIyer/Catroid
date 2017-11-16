/*
 * Catroid: An on-device visual programming system for Android devices
 * Copyright (C) 2010-2017 The Catrobat Team
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
package org.catrobat.catroid.ui.fragment;

import android.content.Context;

import org.catrobat.catroid.R;
import org.catrobat.catroid.ui.BaseSettingsActivity;

public class FormulaEditorCategoryListFragment extends BaseFormulaEditorCategoryListFragment {

	@Override
	protected void initSensors() {
		FormulaEditorCategory category = getCategory(SENSOR_TAG);

		Context context = getActivity().getApplicationContext();

		if (BaseSettingsActivity.isPhiroSharedPreferenceEnabled(context)) {
			category.addCategoryIfDoesNotExist(getString(R.string.formula_editor_device_phiro), PHIRO_SENSOR_ITEMS, createEmptyParametersList(PHIRO_SENSOR_ITEMS.length));
		}

		categoryMap.put(SENSOR_TAG, category);

		super.initSensors();
	}
}