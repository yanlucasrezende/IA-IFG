import pandas as pd
from sklearn.model_selection import train_test_split
from sklearn.preprocessing import StandardScaler
from sklearn.neural_network import MLPRegressor
from sklearn.metrics import mean_squared_error, r2_score
import argparse

def load_and_preprocess(csv_path):
    df = pd.read_csv(csv_path)
    df = df.dropna(subset=['horsepower']).dropna()
    X = df.drop(columns=['horsepower'])
    y = df['horsepower']
    X_train, X_test, y_train, y_test = train_test_split(
        X, y, test_size=0.2, random_state=42
    )
    scaler = StandardScaler()
    X_train = scaler.fit_transform(X_train)
    X_test = scaler.transform(X_test)
    return X_train, X_test, y_train, y_test


def train_and_evaluate(csv_path):
    X_train, X_test, y_train, y_test = load_and_preprocess(csv_path)
    model = MLPRegressor(
        hidden_layer_sizes=(100, 50),
        activation='relu', solver='adam',
        max_iter=500, random_state=42
    )
    model.fit(X_train, y_train)
    preds = model.predict(X_test)
    print("Sklearn MLPRegressor Results:")
    print(f"MSE: {mean_squared_error(y_test, preds):.3f}")
    print(f"R2:  {r2_score(y_test, preds):.3f}")


if __name__ == '__main__':
    parser = argparse.ArgumentParser(
        description='Train and eval MLPRegressor for horsepower prediction.'
    )
    parser.add_argument('csv_path', type=str, help='Path to CSV file')
    args = parser.parse_args()
    train_and_evaluate(args.csv_path)