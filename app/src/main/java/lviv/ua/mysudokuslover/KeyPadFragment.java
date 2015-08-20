package lviv.ua.mysudokuslover;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


/**
 * Created by ipeople on 19.08.15.
 */
public class KeyPadFragment extends DialogFragment  {


    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {


        View v = getActivity().getLayoutInflater()
                .inflate(R.layout.keypad_fragment, null);



        Dialog dialog = new AlertDialog.Builder(getActivity())
//                .setView(v)
                .setTitle(R.string.chose_namber_title)
                .create();

        return dialog;
    }



}
